package com.odroid.movieready.view.layout

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PopularMoviesScreen(navigator: DestinationsNavigator,
                        exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getPopularMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel
    )
}

@Destination
@Composable
fun TopRatedMoviesScreen(navigator: DestinationsNavigator,
                        exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getTopRatedMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel
    )
}

@Destination
@Composable
fun NowPlayingMoviesScreen(navigator: DestinationsNavigator,
                         exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getNowPlayingMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel
    )
}

@Destination
@Composable
fun UpcomingMoviesScreen(navigator: DestinationsNavigator,
                           exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getUpcomingMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel
    )
}