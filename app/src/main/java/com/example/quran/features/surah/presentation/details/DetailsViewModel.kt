package com.example.quran.features.surah.presentation.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.quran.domain.usecases.GetSurahDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: GetSurahDetailsUseCase,
    @ApplicationContext val context: Context
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
        _state.update {
            it.copy(isLoading = true)
        }
        detailsUseCase.invoke(surahNumber).collect{ surahs ->
            _state.update {
                it.copy(surahs = surahs.first(), isLoading = false)
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
                        if (state == Player.STATE_ENDED) {
                            pause()
                        }
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        if (isPlaying) {
                            startTrackingProgress()
                        }
                    }
                })
            }
    }

    fun setMediaList(url: String) {
         val item = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(item)
        exoPlayer.prepare()
        _duration.value = exoPlayer.duration
    }

    fun togglePlayback() {
        exoPlayer.let {
            if (it.isPlaying) it.pause() else it.play()
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