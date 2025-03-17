package com.example.quran.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SurahDetailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun surahDao(): SurahDao
}