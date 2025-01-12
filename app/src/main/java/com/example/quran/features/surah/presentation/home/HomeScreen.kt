package com.example.quran.features.surah.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.getSurahs()
    val surahs = viewModel.surahs.collectAsStateWithLifecycle()

    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(surahs.value.size) { index ->
            Text(text = surahs.value[index].name)
        }
    }
}