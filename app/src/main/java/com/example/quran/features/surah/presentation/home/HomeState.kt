package com.example.quran.features.surah.presentation.home

import com.example.quran.domain.model.Surah

data class HomeState(
    val surahs: List<Surah> = emptyList(),
    val currentPage: Int = 1,
    val isLoading: Boolean = false

)