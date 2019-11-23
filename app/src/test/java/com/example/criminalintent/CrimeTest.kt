package com.example.criminalintent

import org.junit.Test

import org.junit.Assert.*


class CrimeTest {

    @Test
    fun crimeIsNotSolvedByDefault() {
        assertFalse(Crime().isSolved)
    }
}