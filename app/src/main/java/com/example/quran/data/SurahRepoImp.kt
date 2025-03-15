package com.example.quran.data

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
    override suspend fun getSurahs(surahNumber: Int): Flow<List<Surah>>  = flow {
        val surah = mutableListOf<Surah>()
        for (i in surahNumber..10){
            val surahResponse: SurahResponse = client.get("surah/$i").body()
            surah.add(surahResponse.data[i])
            emit(surah)
        }
    }

    override suspend fun getSurahDetails(surahNumber: Int): Flow<Surah>{
        return flow {
            val surahResponse: SurahDetailsResponse = client.get("surah/$surahNumber/ar.alafasy").body()
            emit(surahResponse.data)
        }
    }
}


