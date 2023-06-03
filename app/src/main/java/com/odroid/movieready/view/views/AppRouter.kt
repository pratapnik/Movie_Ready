package com.odroid.movieready.view.views

import androidx.navigation.NavController
import com.odroid.movieready.model.MovieSuggestionModel
import com.odroid.movieready.view.destinations.MovieDetailsModalDestination
import com.ramcosta.composedestinations.navigation.navigate

class AppRouter(val navController: NavController) {
    fun openMovieDetailsBottomSheet(movieSuggestionModel: MovieSuggestionModel) {
        navController.navigate(MovieDetailsModalDestination(movieSuggestionModel = movieSuggestionModel))
    }
}