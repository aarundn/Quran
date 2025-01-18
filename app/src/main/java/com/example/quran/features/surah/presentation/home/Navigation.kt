package com.example.quran.features.surah.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.quran.common.navigation.Destination.Home
import com.example.quran.core.QuranAppState

fun NavController.navigateToHomeScreen() {
    navigate(Home)
}
fun NavGraphBuilder.homeRoute( appState: QuranAppState) {
    composable<Home> {
        HomeScreen(appState = appState, goToDetails = { surahId ->
            appState.goToDetail(surahId = surahId)
        })
    }
}