package com.odroid.movieready.view.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.view.widget.FullWidthItemWidget
import com.odroid.movieready.view.widget.VerticalItemWidget
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ItemListWidget(
    navigator: DestinationsNavigator,
    sourceType: SourceType,
    exploreViewModel: ExploreViewModel
) {
    val moviesList = exploreViewModel.getTrendingMoviesPagination().collectAsLazyPagingItems()

    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        items(moviesList.itemCount) { index ->
            moviesList[index]?.let {
                VerticalItemWidget(
                    navigator, it, exploreViewModel
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemList() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ItemListWidget(
            rememberNavController() as DestinationsNavigator,
            SourceType.TRENDING_MOVIES,
            ExploreViewModel()
        )
    }
}