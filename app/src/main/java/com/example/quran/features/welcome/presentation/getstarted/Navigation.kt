package com.example.quran.features.welcome.presentation.getstarted


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.quran.common.navigation.Destination.GetStarted


fun NavGraphBuilder.getStartedRoute() {
    composable<GetStarted> {
       GetStartedScreen()
    }
}