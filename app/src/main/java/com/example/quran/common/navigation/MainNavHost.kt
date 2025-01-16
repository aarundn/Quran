package com.example.quran.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quran.features.surah.presentation.details.DetailsScreen
import com.example.quran.features.surah.presentation.home.HomeScreen
import com.example.quran.features.welcome.presentation.getstarted.GetStartedScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
    )
    {
        composable(route = Destination.GetStarted.route) { GetStartedScreen(navController = navController) }
        composable(route = Destination.Home.route) { HomeScreen(navController = navController) }
        composable(route = Destination.Details.route,
                arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailsScreen(itemId = itemId)
        }
    }

}
