package com.example.quran.features.surah.presentation.details

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.quran.common.navigation.Destination.Details


fun NavController.navigateToDetailsScreen(surahId : Int) {
    Log.d("Details", "detailsRoute: $surahId")
    navigate(Details(surahId))
}
fun NavGraphBuilder.detailsRoute() {
    composable<Details> { backStackEntry ->
        Log.d("Details", "detailsRoute: $backStackEntry")
        val detailRoute = backStackEntry.toRoute<Details>()
        Log.d("Details", "detailsRoute: ${detailRoute.surahId}")

        DetailsScreen(itemId = detailRoute.surahId.toString())
    }
}