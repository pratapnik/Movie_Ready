package com.odroid.movieready.view.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.odroid.movieready.R
import com.odroid.movieready.entity.SourceType
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.theming.primaryButtonColor
import com.odroid.movieready.view_intent.EntertainmentCategory
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Preview
@Composable
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        MoviesExploreScreen(rememberNavController() as DestinationsNavigator, ExploreViewModel())
    }
}

@Destination(start = true)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesExploreScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel
) {
    val entertainmentCategories = exploreViewModel.getMoviesCategories()
    val selectedChip = rememberSaveable {
        mutableStateOf(SourceType.POPULAR_MOVIES)
    }
    Column {
        LazyRow(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            items(entertainmentCategories) { list: EntertainmentCategory ->
                Card(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable {
                            selectedChip.value = list.category
                        },
                    border = BorderStroke(
                        2.dp,
                        colorResource(id = R.color.primary_color_dark_mode)
                    ), shape = RoundedCornerShape(16.dp),
                    backgroundColor = if (selectedChip.value == list.category) primaryButtonColor else Color.White
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                    ) {
                        Image(
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp),
                            painter = painterResource(id = list.icon),
                            contentDescription = "contentDescription"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = list.title,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.font_bold)),
                                fontSize = 14.sp,
                                color = if (selectedChip.value == list.category) Color.White else primaryAppTextColor
                            )
                        )
                    }
                }
            }
        }
        when(selectedChip.value) {
            SourceType.POPULAR_MOVIES -> PopularMoviesScreen(navigator = navigator, exploreViewModel = exploreViewModel)
            SourceType.TOP_RATED -> TopRatedMoviesScreen(navigator = navigator, exploreViewModel = exploreViewModel)
            SourceType.NOW_PLAYING -> NowPlayingMoviesScreen(navigator = navigator, exploreViewModel = exploreViewModel)
            SourceType.UPCOMING_MOVIES -> UpcomingMoviesScreen(navigator = navigator, exploreViewModel = exploreViewModel)
            else -> {}
        }
    }
}
