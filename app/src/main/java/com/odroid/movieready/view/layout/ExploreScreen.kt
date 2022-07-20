package com.odroid.movieready.view.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.odroid.movieready.view_intent.Category
import com.odroid.movieready.view_intent.CategoryWithList
import com.odroid.movieready.view_model.ExploreViewModel

@Preview
@Composable
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ExploreScreen(getCategoriesWithList(), ExploreViewModel())
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExploreScreen(
    categoriesWithList: List<CategoryWithList>,
    exploreViewModel: ExploreViewModel
) {
    val selectedChip = rememberSaveable {
        mutableStateOf(Category.TRENDING)
    }
    Column {
        LazyRow(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            items(categoriesWithList) { list: CategoryWithList ->
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
                    backgroundColor = if (selectedChip.value == list.category) Color.LightGray else Color.White
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
                                color = colorResource(R.color.primary_text_color)
                            )
                        )
                    }
                }
            }
        }
        val moviesList = categoriesWithList.find {
            it.category == selectedChip.value
        }?.list
        moviesList?.let {
            MoviesListWidget(
                it, exploreViewModel
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MoviesListWidget(
    moviesList: ArrayList<MovieResponse>,
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
        items(moviesList) { movie: MovieResponse ->
            MovieWidget(
                movieResponse = movie,
                exploreViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieWidget(
    movieResponse: MovieResponse,
    exploreViewModel: ExploreViewModel
) {
    Card(
        modifier = Modifier
            .height(300.dp)
            .width(150.dp)
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


fun getCategoriesWithList(): List<CategoryWithList> {
    return listOf(
        CategoryWithList.Trending,
        CategoryWithList.NowPlaying,
        CategoryWithList.Popular,
        CategoryWithList.TopRated
    )
}
