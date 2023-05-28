package com.odroid.movieready.view.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.odroid.movieready.view.components.LastSuggestedMovieView

@Composable
fun GameplayTopView(
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String,
    lastMovieClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LastSuggestedMovieView(
            modifier = Modifier.fillMaxWidth(),
            lastSuggestedMovieName = lastSuggestedMovieName,
            lastSuggestedMoviePosterUrl = lastSuggestedMoviePosterUrl,
            lastMovieClicked = {
                lastMovieClicked.invoke()
            }
        )
    }
}

@Preview
@Composable
fun GameplayTopViewPreview() {
    GameplayTopView(
        lastSuggestedMovieName = "Race 3",
        lastSuggestedMoviePosterUrl = "https://upload.wikimedia.org/wikipedia/en/2/23/Ganga_Tere_Desh_Mein.jpg",
        lastMovieClicked = {}
    )
}