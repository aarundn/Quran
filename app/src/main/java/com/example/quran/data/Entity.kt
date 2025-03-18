package com.example.quran.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quran.domain.model.Ayahs


@Entity(tableName = "surah_details")
data class SurahDetailEntity(
    @PrimaryKey val number: Int,
    val text: String,
    val audioUrl: String?, // Store the audio URL for offline playback
)

@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey val number: Int,
    val name: String,
    val englishName: String,
    val numberOfAyahs: Int,
    val revelationType: String,
    val ayahs: List<Ayahs>
)
