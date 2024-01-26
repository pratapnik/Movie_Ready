package com.odroid.movieready.view.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.odroid.movieready.R
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontMedium
import com.odroid.movieready.view.components.MovieLongTextSection


@Preview
@Composable
fun AllMovieInfoPreview() {
    AllMovieInfo(dumbCharadesSuggestionUiModel = DumbCharadesSuggestionUiModel.preview)
}


@Composable
fun AllMovieInfo(dumbCharadesSuggestionUiModel: DumbCharadesSuggestionUiModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = IshaaraColors.background_color_FFFFFF
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = dumbCharadesSuggestionUiModel.title, style = TextStyle(
                        color = IshaaraColors.primary_app_dark_text_color,
                        fontSize = 28.sp,
                        lineHeight = 32.sp,
                        fontFamily = fontBold,
                        letterSpacing = 1.sp
                    )
                )
                Spacer(modifier = Modifier.size(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_rating),
                            contentDescription = "rating",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "${dumbCharadesSuggestionUiModel.avgRating}/10",
                            style = TextStyle(
                                color = IshaaraColors.primary_app_dark_text_color,
                                fontSize = 16.sp,
                                fontFamily = fontMedium
                            )
                        )

                    }
                    if (dumbCharadesSuggestionUiModel.ratingCount > 0) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_people),
                                contentDescription = "vote count",
                                tint = Color(0xFFf28705),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "${dumbCharadesSuggestionUiModel.ratingCount}+ ratings",
                                style = TextStyle(
                                    color = IshaaraColors.primary_app_dark_text_color,
                                    fontSize = 16.sp,
                                    fontFamily = fontMedium
                                )
                            )
                        }
                    }
                    if (dumbCharadesSuggestionUiModel.releaseDate.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_date),
                                contentDescription = "release date",
                                tint = Color(0xFFf28705),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = dumbCharadesSuggestionUiModel.releaseDate,
                                style = TextStyle(
                                    color = IshaaraColors.primary_app_dark_text_color,
                                    fontSize = 16.sp,
                                    fontFamily = fontMedium
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            if (dumbCharadesSuggestionUiModel.overview.isNotEmpty()) {
                MovieLongTextSection(
                    title = "Overview",
                    description = dumbCharadesSuggestionUiModel.overview,
                    shouldShowFull = false,
                    maxLines = 4
                )
            }
        }
    }
}