package com.odroid.movieready.view.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.odroid.movieready.R
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.view.widget.VerticalItemWidget
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ItemListWidget(
    navigator: DestinationsNavigator,
    moviesList: LazyPagingItems<TmdbItem>,
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
        items(moviesList.itemCount) { index ->
            moviesList[index]?.let {
                VerticalItemWidget(
                    navigator, it, exploreViewModel
                )
            }
        }
    }
}