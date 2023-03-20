package com.odroid.movieready.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.theming.*

@Composable
fun LastSuggestedMovieView(
    modifier: Modifier = Modifier,
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String
) {
    AnimatedVisibility(visible = lastSuggestedMovieName.isNotEmpty()) {
        Box {
            Card(
                modifier = modifier,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White,
                border = BorderStroke(2.dp, color = grayColor),
                elevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "last \nmovie",
                            style = TextStyle(
                                fontFamily = fontMedium,
                                fontSize = 14.sp,
                                color = grayColor
                            )
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "->",
                            style = TextStyle(
                                fontFamily = fontMedium,
                                fontSize = 20.sp,
                                color = grayColor
                            )
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            text = lastSuggestedMovieName,
                            style = TextStyle(
                                fontFamily = fontMedium,
                                fontSize = 18.sp,
                                color = primaryAppTextColor
                            ),
                            modifier = Modifier.fillMaxWidth(0.6F)
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(lastSuggestedMoviePosterUrl)
                            .crossfade(true)
                            .error(R.drawable.app_icon_img)
                            .build(),
                        placeholder = painterResource(R.drawable.app_icon_img),
                        contentDescription = "last movie image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(81.dp)
                            .height(108.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LastSuggestedMoviePreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        LastSuggestedMovieView(
            lastSuggestedMovieName = "Race 3 Race 3 Race 3 Race 3 Race 3",
            lastSuggestedMoviePosterUrl = "https://upload.wikimedia.org/wikipedia/en/2/23/Ganga_Tere_Desh_Mein.jpg"
        )
    }
}
