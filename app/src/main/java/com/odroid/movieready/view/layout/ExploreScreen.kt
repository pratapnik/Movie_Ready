package com.odroid.movieready.view.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.odroid.movieready.view_intent.CategoryWithList
import com.odroid.movieready.view_model.ExploreViewModel

@Preview
@Composable
fun PreviewExploreScreen() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        ExploreScreen(getCategoriesWithList(), ExploreViewModel())
    }
}

@Composable
fun ExploreScreen(
    categoriesWithList: List<CategoryWithList>,
    exploreViewModel: ExploreViewModel
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(categoriesWithList) { list: CategoryWithList ->
            MoviesListWidget(categoryWithList = list, exploreViewModel)
        }
    }
}

@Composable
fun MoviesListWidget(categoryWithList: CategoryWithList,
                     exploreViewModel: ExploreViewModel) {
    Text(
        text = categoryWithList.title,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.font_bold)),
            fontSize = 20.sp,
            color = colorResource(R.color.primary_text_color)
        )
    )
    val moviesList = categoryWithList.list
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(moviesList) { movie: MovieResponse ->
            MovieWidget(movieResponse = movie, exploreViewModel)
        }
    }
}

@Composable
fun MovieWidget(movieResponse: MovieResponse,
                exploreViewModel: ExploreViewModel) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .padding(end = 8.dp)
            .border(2.dp, Color.DarkGray, RoundedCornerShape(8.dp))
            .clickable {
                exploreViewModel.movieClicked(movieResponse)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
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
        CategoryWithList.NowPlaying
    )
}
