package com.example.quran.features.surah.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quran.R
import com.example.quran.common.components.TopAppBar
import com.example.quran.core.QuranAppState
import com.example.quran.domain.model.Surah
import com.example.quran.features.surah.presentation.components.Banner
import com.example.quran.features.surah.presentation.components.SurahItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    appState: QuranAppState,
    goToDetails: (Int) -> Unit
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = "Al-Fatiah",
                rightIcon = ImageVector.vectorResource(id = R.drawable.menu),
            )
        }
    ) { innerPadding ->
        when {

            state.value.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }

            else -> {
                HomeContent(
                    innerPadding,
                    state.value.surahs,
                    appState = appState,
                    goToDetails = goToDetails
                )
            }
        }
    }

}

@Composable
private fun HomeContent(
    innerPadding: PaddingValues,
    surahs: List<Surah>,
    appState: QuranAppState,
    goToDetails: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "Asslamualaikum",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
        item {
            Banner(modifier = Modifier)
        }
        items(surahs.size) { index ->
            val currentSurahs = surahs[index]
            SurahItem(
                number = currentSurahs.number,
                arabicName = currentSurahs.name,
                englishName = currentSurahs.englishName,
                surahType = currentSurahs.revelationType,
                versesCount = currentSurahs.numberOfAyahs,
                onClick = {
                    goToDetails(currentSurahs.number)
                }
            )
        }
    }
}




