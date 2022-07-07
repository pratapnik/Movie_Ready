package com.odroid.movieready.view.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view_intent.getMoviesList
import com.odroid.movieready.view_model.ExploreViewModel

@Preview
@Composable
fun PreviewFavouriteScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        FavouriteScreen(getMoviesList(), ExploreViewModel())
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FavouriteScreen(
    favouriteMoviesList: List<MovieResponse>,
    exploreViewModel: ExploreViewModel
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState =
        BottomSheetState(BottomSheetValue.Collapsed)
    )
    var movieName = remember { mutableStateOf("movie") }

    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
//        Text(
//            text = "Favourites",
//            style = TextStyle(
//                fontFamily = FontFamily(Font(R.font.font_bold)),
//                fontSize = 20.sp,
//                color = colorResource(R.color.primary_text_color)
//            )
//        )
//        LazyVerticalGrid(
//            cells = GridCells.Fixed(3),
//            modifier = Modifier
//                .fillMaxHeight(0.95f),
//            state = lazyListState
//        ) {
//            item {
//                ScrollableTopAppBar(lazyListState = lazyListState)
//            }
//            items(favouriteMoviesList) { movie: MovieResponse ->
//                MovieWidget(
//                    movieResponse = movie,
//                    exploreViewModel,
//                    bottomSheetScaffoldState, coroutineScope, movieName
//                )
//            }
//        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.95f),
            state = lazyListState
        ) {
            item {
                ScrollableTopAppBar(lazyListState = lazyListState, "Your favourites")
            }
            items(favouriteMoviesList) { movie: MovieResponse ->
                FavouriteMovieWidget(
                    movieResponse = movie,
                    exploreViewModel
                )
            }
        }
    }
}

@Composable
fun FavouriteMovieWidget(
    movieResponse: MovieResponse,
    exploreViewModel: ExploreViewModel
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(end = 4.dp, bottom = 4.dp)
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieResponse.posterUrl)
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
                    text = movieResponse.title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.font_bold)),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}