package com.odroid.movieready.view.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.odroid.movieready.view.components.LastSuggestedMovieView

@Composable
fun GameplayTopView(
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LastSuggestedMovieView(
            modifier = Modifier.fillMaxWidth(),
            lastSuggestedMovieName = lastSuggestedMovieName,
            lastSuggestedMoviePosterUrl = lastSuggestedMoviePosterUrl
        )
    }
}