package com.odroid.movieready.view_intent

import com.odroid.movieready.R
import com.odroid.movieready.view.layout.destinations.MoviesExploreScreenDestination
import com.odroid.movieready.view.layout.destinations.SavedItemsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

sealed class BottomNavItem(var title: String, var icon: Int, val direction: DirectionDestinationSpec) {

    object Movies : BottomNavItem("Movies", R.drawable.ic_play_button, MoviesExploreScreenDestination)
    object TvShows : BottomNavItem("Tv Shows", R.drawable.ic_play_button, MoviesExploreScreenDestination)
    object Saved : BottomNavItem("Saved", R.drawable.ic_play_button,
        SavedItemsScreenDestination
    )
}