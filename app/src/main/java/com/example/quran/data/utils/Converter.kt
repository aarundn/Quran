package com.example.quran.data.utils


import androidx.room.TypeConverter
import com.example.quran.domain.model.Ayahs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


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


class AudioDownloader(private val context: Context) {

    private val client = OkHttpClient()

    fun downloadAudio(audioUrl: String, ayahNumber: Int): String? {
        try {
            val request = Request.Builder().url(audioUrl).build()
            val response = client.newCall(request).execute()

            if (!response.isSuccessful) return null

            val inputStream: InputStream? = response.body?.byteStream()
            val file = File(context.filesDir, "ayah_$ayahNumber.mp3")
            val outputStream = FileOutputStream(file)

            inputStream?.copyTo(outputStream)

            outputStream.close()
            inputStream?.close()

            return file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}