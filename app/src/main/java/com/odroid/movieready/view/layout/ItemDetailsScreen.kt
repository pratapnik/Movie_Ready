package com.odroid.movieready.view.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Preview
@Composable
fun PreviewItemDetailsScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ItemDetailsScreen(rememberNavController() as DestinationsNavigator, ExploreViewModel())
    }
}

@Destination
@Composable
fun ItemDetailsScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Item Details Screen with movie: ")
        }
    }

}