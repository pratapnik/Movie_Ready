package com.odroid.movieready.view.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
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
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        MoviesExploreScreen(rememberNavController() as DestinationsNavigator, ExploreViewModel())
    }
}

@Destination
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesExploreScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel
) {

}
