package com.example.quran.features.surah.presentation.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.quran.R
import com.example.quran.common.components.LoadingIndicator
import com.example.quran.common.components.TopAppBar
import com.example.quran.core.QuranAppState
import com.example.quran.domain.model.Surah
import com.example.quran.domain.model.SurahEntity
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
    val surahs = viewModel.pagedSurahs.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = stringResource(R.string.quran_app),
                rightIcon = ImageVector.vectorResource(id = R.drawable.menu),
                leftIcon = ImageVector.vectorResource(id = R.drawable.search),
                onClick = {
                    // TODO: Opening the drawer
                }
            )
        }
    ) { innerPadding ->

                HomeContent(
                    innerPadding,
                    surahs = surahs,
                    appState = appState,
                    isLoadingMore = state.value.isLoadingMore,
                    onLoadMore = {  },
                    goToDetails = goToDetails
                )

        }
    }



@Composable
private fun HomeContent(
    innerPadding: PaddingValues,
    surahs: LazyPagingItems<Surah>,
    isLoadingMore: Boolean = false,
    appState: QuranAppState,
    onLoadMore: (Int) -> Unit = {},
    goToDetails: (Int) -> Unit
) {

//    val listState = rememberLazyListState()
//
//    val shouldLoadMore by remember {
//        derivedStateOf {
//            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
//            lastVisibleIndex >= surahs.size - 1 && surahs.isNotEmpty()
//        }
//    }
//    if (shouldLoadMore && surahs.size < 114) {
//        onLoadMore(surahs.size)
//    }
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = stringResource(R.string.asslamualaikum),
                style = MaterialTheme.typography.headlineMedium,
            )
        }
        item {
            Banner(modifier = Modifier)
        }
        items(surahs.itemCount) { index ->
            val currentSurahs = surahs[index]
            SurahItem(
                number = currentSurahs?.number ?: 0,
                arabicName = currentSurahs?.name ?: "",
                englishName = currentSurahs?.englishName ?: "",
                surahType = currentSurahs?.revelationType ?: "",
                versesCount = currentSurahs?.numberOfAyahs ?: 0,
                onClick = {
                    goToDetails(currentSurahs?.number ?: 0)
                }
            )

        }
        when (surahs.loadState.append) {
            is LoadState.Error -> {
                item {
                    Text(text = "Error loading more Surahs")
                }
            }

            is LoadState.Loading -> {
                item {
                    LoadingIndicator()
                }
            }

            else -> {}
        }
    }
}




