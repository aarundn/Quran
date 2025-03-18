package com.example.quran.data.utils


import androidx.room.TypeConverter
import com.example.quran.domain.model.Ayahs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class Converters {

    private val gson = Gson()
    @TypeConverter
    fun ayahsToJson(ayahs: List<Ayahs>?): String {
        return gson.toJson(ayahs)
    }
    @TypeConverter
    fun jsonToAyahs(json: String): List<Ayahs> {
        val type = object : TypeToken<List<Ayahs>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}

