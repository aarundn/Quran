package com.example.quran.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SurahDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurahDetail(surah: SurahDetailEntity)

    @Query("SELECT * FROM surah_details WHERE number = :surahNumber")
    suspend fun getSurahDetail(surahNumber: Int): SurahDetailEntity?

    @Query("SELECT * FROM surah_details")
    fun getAllSurahDetails(): Flow<List<SurahDetailEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM surah_details WHERE number = :surahNumber)")
    suspend fun surahExists(surahNumber: Int): Boolean
}