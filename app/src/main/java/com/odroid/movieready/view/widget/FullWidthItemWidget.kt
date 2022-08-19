package com.odroid.movieready.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.entity.TmdbItem
import com.odroid.movieready.theming.ratingCardColor
import com.odroid.movieready.theming.whiteColor
import com.odroid.movieready.util.posterUrl
import com.odroid.movieready.view.layout.destinations.ItemDetailsScreenDestination
import com.odroid.movieready.view_model.ExploreViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FullWidthItemWidget(
    exploreViewModel: ExploreViewModel,
    navigator: DestinationsNavigator,
    tmdbMovie: TmdbItem
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(end = 4.dp, bottom = 4.dp)
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        onClick = {
            navigator.navigate(ItemDetailsScreenDestination(tmdbMovie.id))
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(tmdbMovie.posterUrl.posterUrl())
                    .crossfade(true)
                    .error(R.drawable.app_icon_img)
                    .build(),
                placeholder = painterResource(R.drawable.app_icon_img),
                contentDescription = "contentDescription",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ), startY = 100f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = tmdbMovie.title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.font_bold)),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
            }
            IconButton(
                onClick = {
                    exploreViewModel.removeMovieFromWatchList(tmdbMovie.id)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_to_watchlist),
                    contentDescription = "remove from watchlist",
                    tint = whiteColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFullWidthItemWidget() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        FullWidthItemWidget(
            ExploreViewModel(),
            rememberNavController() as DestinationsNavigator, TmdbItem(
                234L, "Humsafar", "",
                "", "", 7F, 8L
            )
        )
    }
}