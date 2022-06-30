package com.odroid.movieready.view.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExploreScreen() {
    Text(text = "Explore")
}

@Preview
@Composable
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ExploreScreen()
    }
}