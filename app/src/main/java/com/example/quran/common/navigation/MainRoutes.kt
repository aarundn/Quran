package com.example.quran.common.navigation



sealed interface Destination {

    val route:String
    data object GetStarted:Destination {
        override val route: String
            get() = "getStarted_screen"
    }
    data object Home:Destination {
        override val route: String
            get() = "home_screen"
    }
    data object Details:Destination {
        override val route: String
            get() = "details_screen"
    }
}