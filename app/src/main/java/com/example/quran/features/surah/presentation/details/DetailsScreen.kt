package com.example.quran.features.surah.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.quran.domain.model.Ayahs
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
    val isClicked by remember { mutableStateOf(false) }

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
                title = state.surahs.englishName ?: stringResource(R.string.quran_app),
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
                    state.surahs.ayahs?.let {
                        DetailsContent(
                            ayahs = it,
                            currentPosition = currentPosition,
                            duration = duration,
                            isClicked = isClicked,
                            modifier = modifier,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun DetailsContent(
    ayahs: List<Ayahs>,
    currentPosition: Long,
    duration: Long,
    isClicked: Boolean,
    modifier: Modifier,
    viewModel: DetailsViewModel
) {
    var isClicked1 = isClicked
    LazyColumn(
        modifier = modifier.padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            DetailsBanner()
        }
        items(ayahs.size) { index ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = ayahs[index].text ?: "",
                    modifier = Modifier.clickable {
                        viewModel.togglePlayback()
                        ayahs[index].audio?.let { viewModel.setMediaList(it) }
                        isClicked1 = !isClicked
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (duration > 0) {
                    LinearProgressIndicator(
                        progress = { currentPosition.toFloat() / duration.toFloat() },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}



