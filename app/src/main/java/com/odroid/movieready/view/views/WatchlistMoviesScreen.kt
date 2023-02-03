package com.odroid.movieready.view.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.odroid.movieready.R
import com.odroid.movieready.view.widget.FullWidthItemWidget
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun WatchlistMoviesScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel
) {
    LaunchedEffect(key1 = 0) {
        exploreViewModel.fetchWatchlistMovies()
    }

    val watchlistMoviesUiState by exploreViewModel.watchMoviesStateFlow.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "watchlist",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_bold)),
                fontSize = 20.sp,
                color = colorResource(R.color.primary_text_color)
            )
        )
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            items(watchlistMoviesUiState, key = { item ->
                item.id
            }) { movie ->
                if (movie != null) {
                    FullWidthItemWidget(
                        exploreViewModel,
                        navigator, tmdbMovie = movie
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFavouriteScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        WatchlistMoviesScreen(rememberNavController() as DestinationsNavigator, ExploreViewModel())
    }
}