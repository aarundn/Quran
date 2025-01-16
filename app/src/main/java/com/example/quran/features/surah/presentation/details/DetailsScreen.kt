package com.example.quran.features.surah.presentation.details

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailsScreen(
    itemId : String?,
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
            AudioPlayer(ayahs.value.first().audio!!)
        } else {
            // Show a placeholder or loading text when the list is empty
            Text(
                text = "Loading audio...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

//        LazyColumn (
//            modifier = Modifier.fillMaxSize()
//                .background(MaterialTheme.colorScheme.background)
//                .padding(16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ){
//            item {
//                Text(
//                    text = ayahs.value.joinToString("  ") { it.text.toString() },
//                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
//                    textAlign = TextAlign.Center,
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onBackground,
//                    fontSize = 24.sp,
//                )
//            }
//        }
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
        Text(text = "Audio Player", color = Color.Black)
        Button(onClick = { togglePlayback() }) {
            Text(text = if (isPlaying) "Pause" else "Play")
        }
    }
}
