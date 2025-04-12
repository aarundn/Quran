package com.example.quran.di

import com.example.quran.data.remote.SurahRepoImp
import com.example.quran.domain.repository.SurahRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindSurahRepository(
        surahRepositoryImpl: SurahRepoImp
    ): SurahRepository

}