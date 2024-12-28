package com.odroid.movieready.view.views

import androidx.navigation.NavController
import com.odroid.movieready.view.destinations.MovieDetailScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

class AppRouter(val navController: NavController) {

    fun navigateToMovieDetailScreen(movieId: Long) {
        navController.navigate(MovieDetailScreenDestination(movieId = movieId))
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}