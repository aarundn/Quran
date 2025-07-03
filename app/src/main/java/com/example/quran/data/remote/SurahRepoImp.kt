package com.example.quran.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.quran.domain.model.SurahEntity
import com.example.quran.domain.model.Surah
import com.example.quran.domain.model.SurahDetailsResponse
import com.example.quran.domain.model.SurahResponse
import com.example.quran.domain.model.toEntity
import com.example.quran.domain.repository.SurahRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SurahRepoImp @Inject constructor(
    private val client: HttpClient
) : SurahRepository {

    override fun getPagedSurahs(): Flow<PagingData<Surah>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { SurahPagingSource(client) }
        ).flow
    }


    override suspend fun loadMoreSurahs(surahNumber: Int): Flow<Surah> = flow {

//            val end = minOf(surahNumber + 10, 114) // 114 is the total number of Surahs
//            for (i in surahNumber + 1..end) { // Fetch next 10 Surahs
//                val surahResponse: SurahResponse = client.get("surah/$i").body()
//                Log.d("SurahRepoImp", "loadMoreSurahs: ${surahResponse.data[i]}")
//                emit(surahResponse.data[i])
//            }
    }

    override suspend fun getSurahDetails(surahNumber: Int): Flow<SurahEntity> {
        return flow {
            val surahResponse: SurahDetailsResponse =
                client.get("surah/$surahNumber/ar.alafasy").body()
            emit(surahResponse.data.toEntity())
        }
    }
}