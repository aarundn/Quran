package com.example.quran.features.surah.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.quran.R
import com.example.quran.common.components.TopAppBar
import com.example.quran.common.navigation.Destination
import com.example.quran.domain.model.Surah
import com.example.quran.features.surah.presentation.components.Banner
import com.example.quran.features.surah.presentation.components.SurahItem
import com.example.quran.features.surah.presentation.details.DetailsViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    viewModel.getSurahs()
    val surahs = viewModel.surahs.collectAsStateWithLifecycle()
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
        HomeContent(innerPadding, surahs, navController = navController)
    }

}

@Composable
private fun HomeContent(
    innerPadding: PaddingValues,
    surahs: State<List<Surah>>,
    navController: NavController
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
        items(surahs.value.size) { index ->
            val currentSurahs = surahs.value[index]
            SurahItem(
                number = currentSurahs.number,
                arabicName = currentSurahs.name,
                englishName = currentSurahs.englishName,
                surahType = currentSurahs.revelationType,
                versesCount = currentSurahs.numberOfAyahs,
                onClick = {
                    navController.navigate(
                        Destination.Details.createRoute("${currentSurahs.number}")
                    )
                }
            )
        }
    }
}




