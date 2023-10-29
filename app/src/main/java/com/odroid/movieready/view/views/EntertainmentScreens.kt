package com.odroid.movieready.view.views

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.odroid.movieready.util.Constants
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navGraph = "explore_graph", start = true)
@Composable
fun PopularMoviesScreen(navigator: DestinationsNavigator,
                        exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getPopularMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel, Constants.POPULAR_MOVIES_HEADER
    )
}

@Destination(navGraph = "explore_graph")
@Composable
fun TopRatedMoviesScreen(navigator: DestinationsNavigator,
                        exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getTopRatedMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel, Constants.TOP_RATED_MOVIES_HEADER
    )
}

@Destination(navGraph = "explore_graph")
@Composable
fun NowPlayingMoviesScreen(navigator: DestinationsNavigator,
                         exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getNowPlayingMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel, Constants.NOW_PLAYING_MOVIES_HEADER
    )
}

@Destination(navGraph = "explore_graph")
@Composable
fun UpcomingMoviesScreen(navigator: DestinationsNavigator,
                           exploreViewModel: ExploreViewModel) {
    val moviesList = exploreViewModel.getUpcomingMoviesPagination().collectAsLazyPagingItems()
    ItemListWidget(
        navigator, moviesList, exploreViewModel, Constants.UPCOMING_MOVIES_HEADER
    )
}