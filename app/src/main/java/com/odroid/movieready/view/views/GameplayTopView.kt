package com.odroid.movieready.view.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.odroid.movieready.view.components.LastSuggestedMovieView

@Composable
fun GameplayTopView(
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String,
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LastSuggestedMovieView(
            modifier = Modifier.padding(start = 32.dp, end = 32.dp),
            lastSuggestedMovieName = lastSuggestedMovieName,
            lastSuggestedMoviePosterUrl = lastSuggestedMoviePosterUrl,
        )
    }
}

@Preview
@Composable
fun GameplayTopViewPreview() {
    GameplayTopView(
        lastSuggestedMovieName = "Race 3",
        lastSuggestedMoviePosterUrl = "https://upload.wikimedia.org/wikipedia/en/2/23/Ganga_Tere_Desh_Mein.jpg",
    )
}