package com.example.quran.features.surah.presentation.details

import com.example.quran.domain.model.Surah

data class DetailsState(
    val surahs: Surah = Surah(),
    val isLoading: Boolean = false
)