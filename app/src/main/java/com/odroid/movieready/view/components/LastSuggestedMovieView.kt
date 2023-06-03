package com.odroid.movieready.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.IshaaraShapes
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontMedium

@Composable
fun LastSuggestedMovieView(
    modifier: Modifier = Modifier,
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String,
    lastMovieClicked: () -> Unit
) {
    val animationTime = 300
    AnimatedVisibility(
        visible = lastSuggestedMovieName.isNotEmpty(), enter = slideInHorizontally(
            initialOffsetX = { -600 }, // small slide 300px
            animationSpec = tween(
                durationMillis = animationTime,
                easing = LinearEasing // interpolator
            )
        )
    ) {
        Box(
            modifier = modifier
                .shadow(24.dp)
                .clickable {
                    lastMovieClicked.invoke()
                }
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF232526),
                            Color(0xFF414345)
                        ),
                    ), shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(84.dp)
                            .height(104.dp), contentAlignment = Alignment.BottomCenter
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(lastSuggestedMoviePosterUrl)
                                .crossfade(true)
                                .error(R.drawable.app_icon_img)
                                .build(),
                            placeholder = painterResource(R.drawable.app_icon_img),
                            contentDescription = "last movie image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(84.dp)
                                .height(104.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "tap to know more",
                                style = TextStyle(
                                    fontFamily = fontMedium,
                                    fontSize = 10.sp,
                                    color = IshaaraColors.primary_app_light_text_color
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(IshaaraShapes.default.roundedCornerXSmall)
                                    .background(
                                        color = IshaaraColors.bottom_sheet_background_0E1110.copy(
                                            alpha = 0.5F
                                        )
                                    )
                                    .padding(top = 4.dp, bottom = 4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp))
                    Column {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = IshaaraColors.background_color_FFFFFF,
                                    shape = IshaaraShapes.default.roundedCornerMedium
                                )
                                .padding(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "previous movie",
                                style = TextStyle(
                                    fontFamily = fontMedium,
                                    fontSize = 10.sp,
                                    color = IshaaraColors.primary_app_dark_text_color
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = lastSuggestedMovieName,
                            style = TextStyle(
                                fontFamily = fontBold,
                                fontSize = 18.sp,
                                color = IshaaraColors.primary_app_light_text_color
                            ),
                            maxLines = 2
                        )
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun LastSuggestedMoviePreview() {
    Box(
        modifier = Modifier
            .background(color = IshaaraColors.background_color_FFFFFF)
            .padding(end = 32.dp, bottom = 32.dp, top = 32.dp)
    ) {
        LastSuggestedMovieView(
            lastSuggestedMovieName = "Race 3 Race 3 Race 3 Race 3 Race 3",
            lastSuggestedMoviePosterUrl = "https://upload.wikimedia.org/wikipedia/en/2/23/Ganga_Tere_Desh_Mein.jpg",
            lastMovieClicked = {}
        )
    }
}
