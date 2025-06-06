package com.example.quran.features.surah.presentation.details

import com.example.quran.domain.model.SurahEntity


data class DetailsState(
    val surahs: SurahEntity? = null,
    val isLoading: Boolean = false,
    val isPlaying: Boolean = false,
    val duration: Float = 0f,
    val currentPosition: Long = 0L,
)