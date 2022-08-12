package com.odroid.movieready.view.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.paging.compose.items
import com.odroid.movieready.R
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.theming.primaryAppColor
import com.odroid.movieready.theming.primaryDarkModeAppTextColor
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
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        stickyHeader {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                backgroundColor = primaryAppColor,
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            ) {
                Text(
                    text = headerTitle,
                    modifier = Modifier.padding(start = 16.dp, top = 6.dp, bottom = 6.dp),
                    style = TextStyle(
                        color = primaryDarkModeAppTextColor,
                        fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.font_bold))
                    )
                )
            }
        }
        items(moviesList) { movieItem ->
            if (movieItem != null) {
                VerticalItemWidget(
                    navigator, movieItem, exploreViewModel
                )
            }
        }
    }
}