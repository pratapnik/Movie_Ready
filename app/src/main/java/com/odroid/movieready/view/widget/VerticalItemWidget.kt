package com.odroid.movieready.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.entity.MovieResponse
import com.odroid.movieready.view_intent.getMoviesList
import com.odroid.movieready.view_model.ExploreViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VerticalItemWidget(
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

@Preview
@Composable
fun PreviewVerticalItem() {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        VerticalItemWidget(getMoviesList()[0], ExploreViewModel())
    }
}