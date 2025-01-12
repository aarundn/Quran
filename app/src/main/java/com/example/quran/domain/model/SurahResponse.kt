package com.example.quran.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SurahResponse(
    val status: String,
    val data: List<Surah>
)