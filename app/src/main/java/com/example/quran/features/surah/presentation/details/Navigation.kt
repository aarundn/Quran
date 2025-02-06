package com.example.quran.features.surah.presentation.details


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.quran.common.navigation.Destination.Details
import com.example.quran.core.QuranAppState



fun NavController.navigateToDetailsScreen(surahId : Int) {
    navigate(Details(surahId = surahId))
}
fun NavGraphBuilder.detailsRoute(appState: QuranAppState) {
    composable<Details> { backStackEntry ->
        val surahId = backStackEntry.toRoute<Details>().surahId
        DetailsScreen(
            surahId = surahId,
            onBack = appState.mainNavController::popBackStack,
            )
    }
}