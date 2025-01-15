package com.example.quran.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Surah(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val numberOfAyahs: Int,
    val revelationType: String,
)