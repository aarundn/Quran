package com.example.quran.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quran.common.navigation.Destination
import com.example.quran.features.surah.presentation.details.navigateToDetailsScreen
import com.example.quran.features.surah.presentation.home.navigateToHomeScreen

@Composable
fun rememberQuranAppState(
    mainStartDestination: Destination,
    mainNavController: NavHostController = rememberNavController(),
): QuranAppState {
    return remember(
        mainStartDestination,
        mainNavController,
    ) {
        QuranAppState(
            mainStartDestination = mainStartDestination,
            mainNavController = mainNavController
        )
    }
}

class QuranAppState(
    val mainStartDestination: Destination,
    val mainNavController: NavHostController,
) {


    val canMainNavigateUp: Boolean
        @Composable get() = mainNavController.previousBackStackEntry != null

    fun mainNavigateUp() = mainNavController.navigateUp()
    fun goToHome() = mainNavController.navigateToHomeScreen()
    fun goToDetail(surahId: Int) = mainNavController.navigateToDetailsScreen(surahId = surahId)
}