package com.odroid.movieready.view.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.theming.primaryAppColor
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.util.DummyDestinationsNavigator
import com.odroid.movieready.util.posterUrl
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Preview
@Composable
fun PreviewItemDetailsScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ItemDetailsScreen(
            DummyDestinationsNavigator(),
            ExploreViewModel(),
            123L
        )
    }
}

@Destination(navGraph = "explore_graph")
@Composable
fun ItemDetailsScreen(
    navigator: DestinationsNavigator,
    exploreViewModel: ExploreViewModel,
    movieId: Long
) {
    LaunchedEffect(key1 = 0) {
        exploreViewModel.fetchMovieDetails(movieId)
    }
    val movieDetail by exploreViewModel.movieDetail.observeAsState()
    movieDetail?.let {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4F),
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                    elevation = 12.dp
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4F)
                            .background(primaryAppColor),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.posterPath.posterUrl())
                            .crossfade(true)
                            .error(R.drawable.app_icon_img)
                            .build(),
                        placeholder = painterResource(R.drawable.app_icon_img),
                        contentDescription = "movie_detail_poster",
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = it.movieTitle,
                    style = TextStyle(
                        fontFamily = fontBold,
                        fontSize = 24.sp,
                        color = primaryAppTextColor
                    ),
                    modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.description,
                    style = TextStyle(
                        fontFamily = fontRegular,
                        fontSize = 14.sp,
                        color = primaryAppTextColor,
                        lineHeight = 18.sp
                    ),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}