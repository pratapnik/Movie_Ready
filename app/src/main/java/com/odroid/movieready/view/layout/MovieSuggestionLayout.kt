package com.odroid.movieready.view.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R

@Composable
fun TopGreeting(day: String, date: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = day,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_bold)),
                fontSize = 20.sp,
                color = colorResource(R.color.primary_text_color)
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = date,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_bold)),
                fontSize = 20.sp,
                color = colorResource(R.color.primary_text_color)
            )
        )
    }
}

@Composable
fun MovieSuggestionCard(
    posterPath: String,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(posterPath)
                    .crossfade(true)
                    .error(R.drawable.app_icon_img)
                    .build(),
                placeholder = painterResource(R.drawable.app_icon_img),
                contentDescription = contentDescription,
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
                            ), startY = 800f
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
                    text = title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.font_bold)),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}