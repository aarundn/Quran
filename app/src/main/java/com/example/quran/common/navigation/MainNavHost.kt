package com.example.quran.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.quran.features.surah.presentation.details.DetailsScreen
import com.example.quran.features.surah.presentation.home.HomeScreen
import com.example.quran.features.welcome.presentation.getstarted.GetStartedScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Destination.GetStarted.route,
    )
    {
        composable(route = Destination.GetStarted.route) { GetStartedScreen(navController = navController) }
        composable(route = Destination.Home.route) { HomeScreen() }
        composable(route = Destination.Details.route) { DetailsScreen() }
    }

}
