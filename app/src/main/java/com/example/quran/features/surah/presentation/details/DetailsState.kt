package com.example.quran.features.surah.presentation.details

import com.example.quran.data.SurahEntity


data class DetailsState(
    val surahs: SurahEntity? = null,
    val isLoading: Boolean = false
)