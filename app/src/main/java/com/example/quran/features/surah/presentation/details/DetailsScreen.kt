package com.example.quran.features.surah.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quran.R
import com.example.quran.common.components.LoadingIndicator
import com.example.quran.common.components.TopAppBar
import com.example.quran.domain.model.SurahEntity
import com.example.quran.features.surah.presentation.components.AyahItem
import com.example.quran.features.surah.presentation.components.DetailsBanner


@Composable
fun DetailsScreen(
    surahId: Int,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val currentPosition by viewModel.currentPosition.collectAsStateWithLifecycle()
    val duration by viewModel.duration.collectAsStateWithLifecycle()


    LaunchedEffect(surahId) {
        viewModel.getSurahDetails(surahId)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.exoPlayer.release()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = state.surahs?.englishName ?: stringResource(R.string.quran_app),
                rightIcon = ImageVector.vectorResource(R.drawable.ic_back),
                leftIcon = ImageVector.vectorResource(R.drawable.search),
                onClick = onBack
            )
        },

        ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {

            when {
                state.isLoading -> LoadingIndicator()
                else -> {
                    DetailsContent(
                        surah = state.surahs,
                        isPlaying = state.isPlaying,
                        currentPosition = currentPosition,
                        duration = duration,
                        modifier = modifier,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@Composable
fun DetailsContent(
    surah: SurahEntity?,
    isPlaying : Boolean,
    currentPosition: Long,
    duration: Long,
    modifier: Modifier,
    viewModel: DetailsViewModel
) {

    var clickedIndex by remember { mutableIntStateOf(-1) }
    LazyColumn(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            DetailsBanner(
                surahName =  surah?.name ?: "",
                surahType = surah?.revelationType ?: "",
                versesCount = surah?.numberOfAyahs ?: 0,
                englishName = surah?.englishName ?: "",
                modifier = modifier
            )
        }
        surah?.ayahs?.size?.let {
            items(it) { index ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AyahItem(
                        currentPosition = currentPosition,
                        duration = duration,
                        ayahs = surah.ayahs[index],
                        audioIcon = if (isPlaying && clickedIndex == index) R.drawable.pause else R.drawable.play,
                        showTimeLine = clickedIndex == index,
                        onClick = {
                            clickedIndex = index
                            viewModel.setMediaList(surah.ayahs[index])
                            viewModel.togglePlayback()
                        }
                    )
                }
            }
        }
    }
}



