package com.example.quran.features.surah.presentation.home

import com.example.quran.domain.model.Surah

data class HomeState(
    val surahs: List<Surah> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore : Boolean = false
)