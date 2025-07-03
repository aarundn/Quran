package com.example.quran.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.quran.domain.model.Surah
import com.example.quran.domain.repository.SurahRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class SurahPagingSource @Inject constructor(
    private val client: HttpClient
): PagingSource<Int, Surah>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Surah> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = client.get("surah?page=$page&pageSize=$pageSize").body<List<Surah>>()
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Surah>): Int? {
        return state.anchorPosition
    }
}