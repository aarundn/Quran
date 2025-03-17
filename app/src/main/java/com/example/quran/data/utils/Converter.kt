package com.example.quran.data.utils


import com.example.quran.domain.model.Ayahs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val gson = Gson()

fun ayahsToJson(ayahs: List<Ayahs>?): String {
    return gson.toJson(ayahs)
}

fun jsonToAyahs(json: String): List<Ayahs> {
    val type = object : TypeToken<List<Ayahs>>() {}.type
    return gson.fromJson(json, type) ?: emptyList()
}