package com.example.quran.domain.model
import kotlinx.serialization.Serializable


@Serializable
data class Surah(
    val number: Int? = null,
    val name: String? = null,
    val englishName: String? = null,
    val numberOfAyahs: Int? = null,
    val revelationType: String? = null,
    val ayahs: List<Ayahs>? = null
)
@Serializable
data class Ayahs(
    val number: Int? = null,
    val text: String? = null,
    val audio: String? = null,
)
