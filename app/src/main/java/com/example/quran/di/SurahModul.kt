package com.example.quran.di


import com.example.quran.data.SurahRepoImp
import com.example.quran.data.network.KtorClient
import com.example.quran.domain.repository.SurahRepository
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
}