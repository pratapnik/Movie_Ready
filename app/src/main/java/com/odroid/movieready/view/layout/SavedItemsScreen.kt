package com.odroid.movieready.view.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.odroid.movieready.R
import com.odroid.movieready.view.widget.FullWidthItemWidget
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun SavedItemsScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel
) {
    val nowPlayingMovies = exploreViewModel.getPopularMoviesPagination().collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "screenTitle",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_bold)),
                fontSize = 20.sp,
                color = colorResource(R.color.primary_text_color)
            )
        )
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxHeight(0.95f)
                .padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            items(nowPlayingMovies) { movie ->
                if (movie != null) {
                    FullWidthItemWidget(
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
        SavedItemsScreen(rememberNavController() as DestinationsNavigator, ExploreViewModel())
    }
}