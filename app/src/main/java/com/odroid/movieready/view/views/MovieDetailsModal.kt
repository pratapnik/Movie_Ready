package com.odroid.movieready.view.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.view.components.IshaaraBottomSheetScaffold
import com.odroid.movieready.view.components.MovieLongTextSection
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet

@Preview
@Composable
fun PreviewMovieDetailsModal() {
    MovieDetailsModal(
        dumbCharadesSuggestionUiModel = DumbCharadesSuggestionUiModel.empty
    )
}

@Destination(style = DestinationStyleBottomSheet::class)
@Composable
fun MovieDetailsModal(dumbCharadesSuggestionUiModel: DumbCharadesSuggestionUiModel) {
//    val movieActors = remember {
//        mutableStateOf(dumbCharadesSuggestionUiModel.actors.getListFromStringUsingSeparator("|"))
//    }
//    val movieGenres = remember {
//        mutableStateOf(dumbCharadesSuggestionUiModel.genres.getListFromStringUsingSeparator("|"))
//    }
    IshaaraBottomSheetScaffold(
        modifier = Modifier.background(IshaaraColors.bottom_sheet_background_0E1110),
        isTopNotchVisible = true
    ) {
        Box(
            modifier = Modifier
                .heightIn(max = (LocalConfiguration.current.screenHeightDp * 0.95).dp)
                .fillMaxWidth()
        ) {
            Column {
                if (dumbCharadesSuggestionUiModel.posterPath.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(dumbCharadesSuggestionUiModel.posterPath)
                            .crossfade(true)
                            .error(R.drawable.app_icon_img)
                            .build(),
                        placeholder = painterResource(R.drawable.app_icon_img),
                        contentDescription = "contentDescription",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxHeight(0.40F)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    if (dumbCharadesSuggestionUiModel.posterPath.isEmpty()) {
                        Spacer(modifier = Modifier.size(28.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = dumbCharadesSuggestionUiModel.title, style = TextStyle(
                                color = IshaaraColors.primary_app_light_text_color,
                                fontSize = 28.sp,
                                lineHeight = 32.sp,
                                fontFamily = fontBold
                            )
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_star_rating_green),
                                contentDescription = "imdb rating"
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = dumbCharadesSuggestionUiModel.avgRating.toString(),
                                style = TextStyle(
                                    color = IshaaraColors.primary_app_light_text_color,
                                    fontSize = 16.sp,
                                    fontFamily = fontRegular
                                )
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_imdb),
                                contentDescription = "",
                                modifier = Modifier.height(20.dp)
                            )
                            Text(
                                text = " Rating",
                                style = TextStyle(
                                    color = IshaaraColors.primary_app_light_text_color,
                                    fontSize = 16.sp,
                                    fontFamily = fontRegular
                                )
                            )

                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (dumbCharadesSuggestionUiModel.ratingCount > 0) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_people),
                                    contentDescription = "release date",
                                    tint = Color(0xFF2ECF80)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "Rated by ${dumbCharadesSuggestionUiModel.ratingCount}",
                                    style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (dumbCharadesSuggestionUiModel.releaseDate.isNotEmpty()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar_tick),
                                    contentDescription = "release date",
                                    tint = Color(0xFF2ECF80)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = dumbCharadesSuggestionUiModel.releaseDate,
                                    style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                        }
//                        if (dumbCharadesSuggestionUiModel.genres.isNotEmpty()) {
//                            MovieChipsSection(
//                                title = "Genres",
//                                items = movieGenres.value
//                            )
//                            Spacer(modifier = Modifier.size(20.dp))
//                        }
//                        if (dumbCharadesSuggestionUiModel.actors.isNotEmpty()) {
//                            MoviesHorizontalCardSection(
//                                title = "Cast (".plus(movieActors.value.size).plus(")"),
//                                items = movieActors.value
//                            )
//                            Spacer(modifier = Modifier.size(20.dp))
//                        }
                        if (dumbCharadesSuggestionUiModel.overview.isNotEmpty()) {
                            MovieLongTextSection(
                                title = "Summary",
                                description = dumbCharadesSuggestionUiModel.overview,
                                shouldShowFull = false,
                                maxLines = 4
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
        }

    }
}
