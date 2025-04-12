package com.example.quran.features.surah.presentation.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.quran.data.local.SurahDao
import com.example.quran.data.utils.AudioDownloader
import com.example.quran.domain.model.Ayahs
import com.example.quran.domain.usecases.GetSurahDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: GetSurahDetailsUseCase,
    @ApplicationContext val context: Context,
    private val surahDao: SurahDao
): ViewModel() {


    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()
    private var _exoPlayer: ExoPlayer? = null
    val exoPlayer: ExoPlayer
        get() = _exoPlayer ?: createPlayer().also { _exoPlayer = it }

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> = _currentPosition

    private val _duration = MutableStateFlow(0L)
    val duration: StateFlow<Long> = _duration


    suspend fun getSurahDetails(surahNumber: Int) {
        viewModelScope.launch(IO) {
            _state.update { it.copy(isLoading = true) }

            val surah = surahDao.getSurahDetail(surahNumber).firstOrNull()

            if (surah != null && surah.ayahs.isNotEmpty()) {
                println("🟢 Using cached data from DB: ${surah.ayahs.size} ayahs found")
                _state.update { it.copy(surahs = surah, isLoading = false) }
            } else {
                detailsUseCase.invoke(surahNumber).collect { surahs ->
                    if (surah != null) {
                        println("🟢 Using cached data from remote: ${surah.ayahs.size} ayahs found")
                    }
                    val ayahsWithLocalAudio = surahs.first().ayahs.map { ayah ->

                        val localPath = ayah.getAudioUrl()?.let {
                            AudioDownloader(context).downloadAudio(it, ayah.number ?: 0)
                        }
                        ayah.copy(localAudioPath = localPath)
                    }

                    val updatedSurah = surahs.first().copy(ayahs = ayahsWithLocalAudio)
                    surahDao.insertSurahDetail(updatedSurah)
                    println("🟢 API Fetch: ${updatedSurah.ayahs.size} ayahs found")
                    _state.update { it.copy(surahs = updatedSurah, isLoading = false) }
                }
            }
        }
    }

    private fun createPlayer(): ExoPlayer {
        return ExoPlayer.Builder(context)
            .build()
            .apply {
                playWhenReady = false
                addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(state: Int) {
                        when (state) {
                            Player.STATE_READY -> {
                                _duration.value = exoPlayer.duration
                            }
                            Player.STATE_IDLE -> {
                                _duration.value = 0
                            }
                            Player.STATE_BUFFERING -> {
                                _state.update {
                                    it.copy(
                                        isPlaying = false
                                    )
                                }
                            }
                        }
                        if (state == Player.STATE_ENDED) {
                            pause()
                            _state.update {
                                it.copy(
                                    isPlaying = false
                                )
                            }
                            exoPlayer.seekTo(0)
                            exoPlayer.pause()
                        }
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        _state.update { state ->
                            state.copy(
                                isPlaying = isPlaying,
                                duration = exoPlayer.duration.toFloat()
                            )
                        }
                        if (isPlaying) {
                            startTrackingProgress()
                        }
                    }
                })
            }
    }

    fun setMediaList(ayah: Ayahs) {
        val url = ayah.localAudioPath ?: ayah.audio ?: return
        Log.d("DetailsViewModel", "setMediaList: $url")
        val item = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(item)
        exoPlayer.prepare()
        _duration.value = exoPlayer.duration
    }

    fun togglePlayback() {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
            _state.update { state ->
                state.copy(
                    isPlaying = exoPlayer.isPlaying
                )
            }
    }

    private fun startTrackingProgress() {
        viewModelScope.launch {
            while (exoPlayer.isPlaying) {
                _currentPosition.value = exoPlayer.currentPosition
                _duration.value = exoPlayer.duration
                delay(10)
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }

}