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
    val ayahs: List<Ayahs>? = null
)
@Serializable
data class Ayahs(
    val number: Int? = null,
    val text: String? = null,
    val audio : String? = null,
)