package com.odroid.movieready.view.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view.widget.VerticalItemWidget
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ItemListWidget(
    navigator: DestinationsNavigator,
    moviesList: ArrayList<MovieResponse>,
    exploreViewModel: ExploreViewModel
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        items(moviesList) { item: MovieResponse ->
            VerticalItemWidget(
                navigator, movieResponse = item, exploreViewModel
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemList() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ItemListWidget(rememberNavController() as DestinationsNavigator,getCategoriesWithList()[0].list, ExploreViewModel())
    }
}