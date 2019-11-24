package com.example.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class CrimeFragmentTest {

    private val mCrime = Crime()

    @Before fun setUp() {
        CrimeRepository.get().addCrime(mCrime)
        launchFragmentInContainer<CrimeFragment>(CrimeFragment.newArguments(mCrime.id))
    }

    @After fun tearDown() {
        CrimeRepository.get().deleteCrime(mCrime)
    }

    @Test fun inputFieldsExistOnEmptyCrimeFragment() {
        onView(withId(R.id.crime_title)).check(matches(withText("")))
        onView(withId(R.id.crime_title)).check(matches(withHint(R.string.crime_title_hint)))
        onView(withId(R.id.crime_date)).check(matches(isDisplayed()))
        onView(withId(R.id.crime_solved)).check(matches(isDisplayed()))
    }
}