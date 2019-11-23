package com.example.criminalintent


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_crime.*
import java.util.*

private const val TAG = "CrimeFragment"
private const val CRIME_ID_KEY = "crime_id_key"
private const val DIALOG_DATE = "dialog_date"
private const val REQUEST_DATE = 0

/**
 * A simple [Fragment] subclass.
 */
class CrimeFragment : Fragment(), DatePickerFragment.Listener {


    private lateinit var crime: Crime

    private lateinit var titleText: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    private val crimeDetailViewModel: CrimeDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CrimeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
        val crimeId = arguments?.getSerializable(CRIME_ID_KEY) as UUID
        Log.d(TAG, "Retrieved crime id in onCreate: $crimeId")
        crimeDetailViewModel.loadCrime(crimeId)
    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }

    private fun updateUI() {
        crime_title.setText(crime.title)
        crime_date.text = crime.date.toString()
        solvedCheckBox.apply {
            isChecked = crime.isSolved
            jumpDrawablesToCurrentState()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        Log.d(tag, "called onCreateView")
        titleText = view.findViewById(R.id.crime_title)
        dateButton = view.findViewById(R.id.crime_date)
        solvedCheckBox = view.findViewById(R.id.crime_solved)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tag, "called onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { crime ->
                crime?.let {
                    this.crime = crime
                    updateUI()
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "called onStart")
        val titleWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        }
        titleText.addTextChangedListener(titleWatcher)

        solvedCheckBox.setOnCheckedChangeListener {
                _, isChecked -> crime.isSolved = isChecked
        }

        dateButton.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply {
                setTargetFragment(this@CrimeFragment, REQUEST_DATE)
                show(this@CrimeFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime)
    }

    companion object {
        fun newInstance(crimeId: UUID) : CrimeFragment {
            val args = Bundle().apply {
                putSerializable(CRIME_ID_KEY, crimeId)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }

}
