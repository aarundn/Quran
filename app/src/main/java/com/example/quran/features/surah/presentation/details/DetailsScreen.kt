package com.example.quran.features.surah.presentation.details

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailsScreen(
    itemId: String?,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.getSurahDetails(itemId?.toInt() ?: 1)
    }
    val ayahs = viewModel.ayahs.collectAsStateWithLifecycle()

    Box(modifier = modifier
        .fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center

        ) {
        if (ayahs.value.isNotEmpty()) {
            AudioPlayer(ayahs.value[2].audio!!)
        } else {
            // Show a placeholder or loading text when the list is empty
            Text(
                text = "Loading audio...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun AudioPlayer(audioUrl: String) {
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }

    // On Composable composition, initialize and start MediaPlayer
    LaunchedEffect(audioUrl) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(audioUrl)
            prepare() // Make sure to call prepare to buffer the audio
        }
    }

    // When the user clicks the Play/Pause button
    fun togglePlayback() {
        if (isPlaying) {
            mediaPlayer?.pause()
        } else {
            mediaPlayer?.start()
        }
        isPlaying = !isPlaying
    }

    // Display the UI for the audio player
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Audio Player", color = MaterialTheme.colorScheme.onBackground, fontSize = 24.sp)
        Button(onClick = { togglePlayback() }) {
            Text(text = if (isPlaying) "Pause" else "Play")
        }
    }
}
