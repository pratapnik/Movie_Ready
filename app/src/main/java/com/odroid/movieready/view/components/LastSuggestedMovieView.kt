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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontMedium
import com.odroid.movieready.theming.grayColor
import com.odroid.movieready.theming.primaryAppTextColor

@Composable
fun LastSuggestedMovieView(
    modifier: Modifier = Modifier,
    lastSuggestedMovieName: String,
    lastSuggestedMoviePosterUrl: String
) {
    AnimatedVisibility(visible = lastSuggestedMovieName.isNotEmpty()) {
        Box(modifier = modifier.padding(top = 20.dp, bottom = 12.dp)) {
            Card(
                modifier = modifier,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White,
                border = BorderStroke(2.dp, color = grayColor),
                elevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "last movie:",
                        style = TextStyle(
                            fontFamily = fontMedium,
                            fontSize = 16.sp,
                            color = primaryAppTextColor
                        )
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = lastSuggestedMovieName,
                        style = TextStyle(
                            fontFamily = fontBold,
                            fontSize = 20.sp,
                            color = primaryAppTextColor
                        )
                    )
                    Spacer(modifier = Modifier.size(12.dp))
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
                            .width(50.dp)
                            .height(90.dp)
                    )
                }
            }
        }


    }
}
