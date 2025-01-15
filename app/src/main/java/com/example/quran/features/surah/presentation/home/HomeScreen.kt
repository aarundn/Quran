package com.example.quran.features.surah.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quran.R
import com.example.quran.common.components.TopAppBar
import com.example.quran.domain.model.Surah
import com.example.quran.features.surah.presentation.components.SurahItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.getSurahs()
    val surahs = viewModel.surahs.collectAsStateWithLifecycle()
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
//        state = rememberTopAppBarState()
//    )
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
        LazyColumn(
            modifier = Modifier.padding(innerPadding).padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = "Asslamualaikum",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            items(surahs.value.size) { index ->
                SurahItem(
                    number = surahs.value[index].number,
                    arabicName = surahs.value[index].name,
                    englishName = surahs.value[index].englishName,
                    surahType = surahs.value[index].revelationType,
                    versesCount = surahs.value[index].numberOfAyahs,
                    onClick = { }
                )
            }
        }
    }

}

@Composable
fun Banner(modifier: Modifier) {

}


@Composable
fun HomeContent(modifier: Modifier, surahs: State<List<Surah>>) {
    LazyColumn(modifier = modifier.padding(16.dp)) {

    }
}
