package com.example.quran.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quran.domain.model.SurahDetailEntity
import com.example.quran.domain.model.SurahEntity
import com.example.quran.data.utils.Converters


@Database(
    entities = [SurahDetailEntity::class, SurahEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun surahDao(): SurahDao
}