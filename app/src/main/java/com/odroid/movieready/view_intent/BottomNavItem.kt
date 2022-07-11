package com.odroid.movieready.view_intent

import com.odroid.movieready.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Movies : BottomNavItem("Movies", R.drawable.ic_next, "Movies")
    object TvShows : BottomNavItem("Tv Shows", R.drawable.ic_play_button, "Tv Shows")
    object Saved : BottomNavItem("Saved", R.drawable.ic_play_button, "Saved")
}