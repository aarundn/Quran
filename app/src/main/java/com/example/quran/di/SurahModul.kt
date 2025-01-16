package com.example.quran.di


import com.example.quran.data.network.KtorClient
import com.example.quran.domain.repository.SurahRepository
import com.example.quran.domain.usecases.GetSurahDetailsUseCase
import com.example.quran.domain.usecases.GetSurahsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SurahModule {

    @Provides
    @Singleton
    fun KtorClientProvides(): KtorClient {
        return KtorClient()
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
}