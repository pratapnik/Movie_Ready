package com.odroid.movieready.view.views

import androidx.navigation.NavController
import com.odroid.movieready.view.destinations.MovieDetailsModalDestination
import com.ramcosta.composedestinations.navigation.navigate

class AppRouter(val navController: NavController) {

    fun openMovieDetailsBottomSheet() {
        navController.navigate(MovieDetailsModalDestination)
    }
}