package com.example.criminalintent

import androidx.fragment.app.FragmentFactory
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
    fun inputFieldsExist() {
        // The "fragmentArgs" and "factory" arguments are optional.
        val crime = Crime()
        val fragmentArgs = CrimeFragment.newArguments(crime.id)
        val factory = FragmentFactory()
        val scenario = launchFragmentInContainer<CrimeFragment>(
            fragmentArgs, factory = factory)
        onView(withId(R.id.crime_title)).check(matches(isDisplayed()))
    }
}