package com.odroid.movieready.view.layout

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.theming.boldFont
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.theming.regularFont
import com.odroid.movieready.util.posterUrl
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Preview
@Composable
fun PreviewItemDetailsScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ItemDetailsScreen(
            rememberNavController() as DestinationsNavigator,
            ExploreViewModel(),
            123L
        )
    }
}

@Destination
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
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.posterPath.posterUrl())
                        .crossfade(true)
                        .error(R.drawable.app_icon_img)
                        .build(),
                    placeholder = painterResource(R.drawable.app_icon_img),
                    contentDescription = "movie_detail_poster",
                    modifier = Modifier
                        .fillMaxWidth(0.8F)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = it.movieTitle,
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontSize = 16.sp,
                        color = primaryAppTextColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.description,
                    style = TextStyle(
                        fontFamily = regularFont,
                        fontSize = 14.sp,
                        color = primaryAppTextColor
                    )
                )
            }
        }
    }

}