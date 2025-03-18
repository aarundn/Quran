package com.example.quran.domain.repository



import com.example.quran.data.SurahEntity
import com.example.quran.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface SurahRepository {
    suspend fun getSurahs(): Flow<List<SurahEntity>>
    suspend fun loadMoreSurahs(surahNumber: Int): Flow<Surah>
    suspend fun getSurahDetails(surahNumber: Int): Flow<SurahEntity>
}