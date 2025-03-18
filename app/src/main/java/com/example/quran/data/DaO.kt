package com.example.quran.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SurahDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurahDetail(surah: SurahEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurah(surah: List<SurahEntity>)

    @Query("SELECT * FROM surah")
    fun getAllSurah(): List<SurahEntity>

    @Query("SELECT * FROM surah WHERE number = :surahNumber")
    fun getSurahDetail(surahNumber: Int): Flow<SurahEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM surah_details WHERE number = :surahNumber)")
    suspend fun surahExists(surahNumber: Int): Boolean
}