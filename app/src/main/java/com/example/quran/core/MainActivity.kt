package com.example.quran.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.quran.common.navigation.Destination
import com.example.quran.common.navigation.MainNavHost
import com.example.quran.core.ui.theme.QuranTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val startDestination = Destination.Home.route
            val navController = rememberNavController()
            QuranTheme {
                        MainNavHost(navController = navController, startDestination = startDestination)
            }
        }
    }
}