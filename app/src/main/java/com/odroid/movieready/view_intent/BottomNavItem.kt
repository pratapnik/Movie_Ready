package com.odroid.movieready.view_intent

import com.odroid.movieready.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Explore : BottomNavItem("Explore", R.drawable.ic_next, "Explore")
    object Favourite : BottomNavItem("Favourite", R.drawable.ic_play_button, "Favourite")
}