package com.example.quran.data

import com.example.quran.data.network.KtorClient
import com.example.quran.domain.model.Ayahs
import com.example.quran.domain.model.Surah
import com.example.quran.domain.repository.SurahRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SurahRepoImp @Inject constructor(
    private val client: KtorClient
): SurahRepository {
    override suspend fun getSurahs(): Flow<List<Surah>>  = flow {
        emit(client.getSurahs())
    }

    override suspend fun getSurahDetails(surahNumber: Int): Flow<List<Ayahs>>{
        return flow {
            emit(client.getSurahDetails(surahNumber))
        }
    }
}