package com.example.criminalintent

import androidx.room.*


@Database(entities = [ Crime::class ], version = 1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {

    abstract fun crimeDao(): CrimeDao

}