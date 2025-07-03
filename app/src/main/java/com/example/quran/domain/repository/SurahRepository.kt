package com.example.quran.domain.repository



import androidx.paging.PagingData
import com.example.quran.domain.model.SurahEntity
import com.example.quran.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface SurahRepository {
    fun getPagedSurahs(): Flow<PagingData<Surah>>
    suspend fun loadMoreSurahs(surahNumber: Int): Flow<Surah>
    suspend fun getSurahDetails(surahNumber: Int): Flow<SurahEntity>
}