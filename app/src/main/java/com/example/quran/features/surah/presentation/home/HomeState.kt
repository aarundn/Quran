package com.example.quran.features.surah.presentation.home

import com.example.quran.domain.model.SurahEntity


data class HomeState(
    val surahs: List<SurahEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore : Boolean = false
)