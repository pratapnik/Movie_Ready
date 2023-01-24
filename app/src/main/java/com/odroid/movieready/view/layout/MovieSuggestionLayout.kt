package com.odroid.movieready.view.layout

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.primaryAppTextColor
import com.odroid.movieready.theming.primaryButtonTextColor

@Composable
fun TopGreeting(
    day: String, date: String,
    shouldShowExploreButton: Boolean,
    onExploreButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = day,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.font_bold)),
                    fontSize = 20.sp,
                    color = primaryAppTextColor
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = date,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.font_bold)),
                    fontSize = 20.sp,
                    color = primaryAppTextColor
                )
            )
        }
        if (shouldShowExploreButton) {
            TextButton(
                onClick = onExploreButtonClick
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "explore",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.font_bold)),
                            fontSize = 16.sp,
                            color = colorResource(R.color.primary_red_color)
                        )
                    )
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .padding(start = 4.dp),
                        painter = painterResource(id = R.drawable.hot),
                        contentDescription = "contentDescription"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LayoutPreview() {
    Column(modifier = Modifier.background(Color.White)) {
        TopGreeting(
            day = "Sunday",
            date = "17 September 1997",
            onExploreButtonClick = {},
            shouldShowExploreButton = false
        )
        MovieSuggestionCard(posterPath = "https://picsum.photos/500/300?random=1",
            contentDescription = "", title = "Cool Boy", onNewMovieButtonClick = { })
    }
}

@Composable
fun MovieSuggestionCard(
    posterPath: String,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
    onNewMovieButtonClick: () -> Unit
) {
    Column {
        val infiniteTransition = rememberInfiniteTransition()

        val scale by infiniteTransition.animateFloat(
            initialValue = 0.95f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse,
            )
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .scale(scale),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.8f)) {
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
                            fontFamily = fontBold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        ExtendedFloatingActionButton(
            onClick = onNewMovieButtonClick,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(24.dp),
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    contentDescription = "next icon"
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.get_movie_button_label),
                    style = TextStyle(
                        color = primaryButtonTextColor,
                        fontSize = 20.sp, fontFamily = fontBold
                    )
                )
            },
            backgroundColor = colorResource(R.color.primary_button_color)
        )
    }
}