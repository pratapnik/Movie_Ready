package com.odroid.movieready.view.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.odroid.movieready.view.components.HideMovieView
import com.odroid.movieready.view.components.MovieCounterView

@Composable
fun TopGreeting(
    shouldShowExploreButton: Boolean,
    onExploreButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ishaara Says Hi!",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.font_bold)),
                    fontSize = 20.sp,
                    color = primaryAppTextColor,
                    letterSpacing = 1.sp
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
            onExploreButtonClick = {},
            shouldShowExploreButton = false
        )
        MovieSuggestionCard(
            posterPath = "https://picsum.photos/500/300?random=1",
            contentDescription = "",
            title = "Cool Boy",
            onNewMovieButtonClick = { },
            movieCounterValue = 1,
            lastSuggestedMovieName = "",
            lastSuggestedMoviePosterUrl = ""
        )
    }
}

@Composable
fun MovieSuggestionCard(
    posterPath: String,
    contentDescription: String,
    title: String,
    movieCounterValue: Int,
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String,
    modifier: Modifier = Modifier,
    onNewMovieButtonClick: () -> Unit
) {
    val movieVisibility = remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val infiniteTransition = rememberInfiniteTransition()

        val scale by infiniteTransition.animateFloat(
            initialValue = 0.98f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse,
            )
        )
        GameplayTopView(
            lastSuggestedMovieName = lastSuggestedMovieName,
            lastSuggestedMoviePosterUrl = lastSuggestedMoviePosterUrl
        )
        Spacer(modifier = Modifier.size(24.dp))
        Box(contentAlignment = Alignment.TopStart) {
            Box(modifier = Modifier.padding(top = 20.dp, start = 12.dp, end = 12.dp)) {
                Card(
                    modifier = modifier
                        .fillMaxWidth(0.95F)
                        .fillMaxHeight(0.75F)
                        .scale(scale),
                    shape = RoundedCornerShape(15.dp),
                    elevation = 24.dp
                ) {
                    Box(modifier = Modifier.fillMaxHeight(0.82F)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(posterPath)
                                .crossfade(true)
                                .error(R.drawable.app_icon_img)
                                .build(),
                            placeholder = painterResource(R.drawable.app_icon_img),
                            contentDescription = contentDescription,
                            contentScale = ContentScale.FillBounds,
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
                                ),
                                modifier = Modifier.fillMaxWidth(0.85F)
                            )
                        }
                    }
                    this@Column.AnimatedVisibility(visible = !movieVisibility.value) {
                        HideMovieView(onUnHideClick = {
                            movieVisibility.value = true
                        })
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp, bottom = 4.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        IconButton(onClick = { movieVisibility.value = !movieVisibility.value }) {
                            Icon(
                                painter = if (!movieVisibility.value) {
                                    painterResource(id = R.drawable.ic_visibility_off)
                                } else {
                                    painterResource(id = R.drawable.ic_visibility)
                                }, contentDescription = "card visibility",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            MovieCounterView(value = movieCounterValue)
        }

        Spacer(modifier = Modifier.height(24.dp))
        ExtendedFloatingActionButton(
            onClick = {
                movieVisibility.value = true
                onNewMovieButtonClick.invoke()
            },
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
        Spacer(modifier = Modifier.height(24.dp))
    }
}