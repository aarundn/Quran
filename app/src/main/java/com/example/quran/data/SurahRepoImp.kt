package com.example.quran.data

import android.annotation.SuppressLint
import android.util.Log
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
        val surah = mutableListOf<Surah>()
//        for (i in 1..10){
            val surahResponse: SurahResponse = client.get("surah").body()
//                surah.add(surahResponse.data)
//        }
        emit(surahResponse.data)
    }

    override suspend fun loadMoreSurahs(surahNumber: Int): Flow<Surah>  = flow {

//            val end = minOf(surahNumber + 10, 114) // 114 is the total number of Surahs
//            for (i in surahNumber + 1..end) { // Fetch next 10 Surahs
//                val surahResponse: SurahResponse = client.get("surah/$i").body()
//                Log.d("SurahRepoImp", "loadMoreSurahs: ${surahResponse.data[i]}")
//                emit(surahResponse.data[i])
//            }
    }

    override suspend fun getSurahDetails(surahNumber: Int): Flow<Surah>{
        return flow {
            val surahResponse: SurahDetailsResponse = client.get("surah/$surahNumber/ar.alafasy").body()
            emit(surahResponse.data)
        }
    }
}


