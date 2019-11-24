package com.example.criminalintent

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CrimeFragmentTest {
    @Test
    fun inputFieldsExistOnEmptyCrimeFragment() {
        val crime = Crime()
        val fragmentArgs = CrimeFragment.newArguments(crime.id)
        launchFragmentInContainer<CrimeFragment>(fragmentArgs)
        onView(withId(R.id.crime_title)).check(matches(withHint(R.string.crime_title_hint)))
        onView(withId(R.id.crime_date)).check(matches(isDisplayed()))
        onView(withId(R.id.crime_solved)).check(matches(isDisplayed()))
    }
}