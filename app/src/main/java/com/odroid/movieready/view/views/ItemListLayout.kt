package com.odroid.movieready.view.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.odroid.movieready.R
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.theming.primaryAppColor
import com.odroid.movieready.theming.primaryDarkModeAppTextColor
import com.odroid.movieready.theming.whiteColor
import com.odroid.movieready.view.views.destinations.WatchlistMoviesScreenDestination
import com.odroid.movieready.view.widget.VerticalItemWidget
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ItemListWidget(
    navigator: DestinationsNavigator,
    moviesList: LazyPagingItems<TmdbItem>,
    exploreViewModel: ExploreViewModel,
    headerTitle: String
) {
    val lazyListState = rememberLazyListState()
    val listState = exploreViewModel.getListState(headerTitle)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val latestLifecycleEvent = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            latestLifecycleEvent.value = event
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    if (latestLifecycleEvent.value == Lifecycle.Event.ON_RESUME) {
        LaunchedEffect(latestLifecycleEvent) {
            lazyListState.animateScrollToItem(
                listState.firstVisibleItemIndex,
                listState.firstVisibleItemScrollOffset
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        state = lazyListState
    ) {
        stickyHeader {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                backgroundColor = primaryAppColor,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = headerTitle,
                        modifier = Modifier.padding(start = 16.dp, top = 6.dp, bottom = 6.dp),
                        style = TextStyle(
                            color = primaryDarkModeAppTextColor,
                            fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.font_bold))
                        )
                    )
                    IconButton(
                        onClick = {
                            navigator.navigate(WatchlistMoviesScreenDestination)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmarks),
                            contentDescription = "watchlist",
                            tint = whiteColor,
                            modifier = Modifier.padding(
                                end = 20.dp, top = 6.dp, bottom = 6.dp,
                                start = 20.dp
                            )
                        )
                    }
                }
            }
        }
        items(moviesList) { movieItem ->
            if (movieItem != null) {
                VerticalItemWidget(
                    navigator, movieItem, exploreViewModel
                )
                {
                    exploreViewModel.updateListState(
                        headerTitle,
                        lazyListState
                    )
                }
            }
        }
    }
}