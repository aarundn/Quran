package com.example.quran.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "surah_details")
data class SurahDetailEntity(
    @PrimaryKey val number: Int,
    val text: String,
    val audioUrl: String?, // Store the audio URL for offline playback
)