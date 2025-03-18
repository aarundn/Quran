package com.example.quran.di


import android.content.Context
import androidx.room.Room
import com.example.quran.data.AppDatabase
import com.example.quran.domain.repository.SurahRepository
import com.example.quran.domain.usecases.GetSurahDetailsUseCase
import com.example.quran.domain.usecases.GetSurahsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object SurahModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun KtorClientProvides(): HttpClient {
        return HttpClient(OkHttp) {
            defaultRequest {
                url("https://api.alquran.cloud/v1/")
            }
            install(Logging) {
                logger = Logger.SIMPLE
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
        }
    }

    @Provides
    @Singleton
    fun provideGetSurahUseCase(
        surahRepository: SurahRepository
    ): GetSurahDetailsUseCase {
        return GetSurahDetailsUseCase(surahRepository)
    }

    @Provides
    @Singleton
    fun provideGetSurahsUseCase(
        surahRepository: SurahRepository
    ): GetSurahsUseCase {
        return GetSurahsUseCase(surahRepository)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "quran_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideSurahDao(appDatabase: AppDatabase) = appDatabase.surahDao()
}