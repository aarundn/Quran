package com.example.quran.common.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {


    @Serializable
    data object GetStarted:Destination
    @Serializable
    data object Home:Destination
    @Serializable
    data class Details(val surahId: Int):Destination

}