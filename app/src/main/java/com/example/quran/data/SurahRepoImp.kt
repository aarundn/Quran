package com.example.quran.data

import com.example.quran.domain.model.Ayahs
import com.example.quran.domain.model.Surah
import com.example.quran.domain.model.SurahDetailsResponse
import com.example.quran.domain.model.SurahResponse
import com.example.quran.domain.repository.SurahRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SurahRepoImp @Inject constructor(
    private val client: HttpClient
): SurahRepository {
    override suspend fun getSurahs(): Flow<List<Surah>>  = flow {

            val surahResponse: SurahResponse = client.get("surah").body()

        emit(surahResponse.data)
    }

    override suspend fun getSurahDetails(surahNumber: Int): Flow<List<Ayahs>>{
        return flow {
            val surahResponse: SurahDetailsResponse = client.get("surah/$surahNumber/ar.alafasy").body()
            surahResponse.data.ayahs?.let { emit(it) }
        }
    }
}


