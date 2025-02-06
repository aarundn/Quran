package com.example.quran.domain.repository


import com.example.quran.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface SurahRepository {
    suspend fun getSurahs(): Flow<List<Surah>>
    suspend fun getSurahDetails(surahNumber: Int): Flow<Surah>
}