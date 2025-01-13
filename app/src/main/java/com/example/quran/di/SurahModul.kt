package com.example.quran.di


import com.example.quran.data.network.KtorClient
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