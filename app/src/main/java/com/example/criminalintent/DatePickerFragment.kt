package com.example.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "arg_date"

class DatePickerFragment : DialogFragment() {
    interface Listener {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            val resultDate = GregorianCalendar(year, month, dayOfMonth).time
            targetFragment?.let { (it as Listener).onDateSelected(resultDate)  }
        }

        val calendar = Calendar.getInstance()
        calendar.time = arguments?.getSerializable(ARG_DATE) as Date
        return DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply { arguments = args }
        }
    }

}