package com.example.quran.data.network

import com.example.quran.domain.model.Ayahs
import com.example.quran.domain.model.Surah
import com.example.quran.domain.model.SurahDetailsResponse
import com.example.quran.domain.model.SurahResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class KtorClient {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url ( "https://api.alquran.cloud/v1/" )
        }
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation){
            json(
                Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
    }

     suspend fun getSurahs(): List<Surah> {
        val surahResponse: SurahResponse = client.get("surah").body()
         return surahResponse.data
    }
    suspend fun getSurahDetails(surahNumber: Int): List<Ayahs> {
        val surahResponse: SurahDetailsResponse = client.get("surah/$surahNumber/ar.alafasy").body()
        return surahResponse.data.ayahs!!
    }
}
