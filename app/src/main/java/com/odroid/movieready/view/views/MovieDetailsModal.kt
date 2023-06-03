package com.odroid.movieready.view.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.odroid.movieready.model.MovieSuggestionModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.util.ViewUtil
import com.odroid.movieready.util.getListFromStringUsingSeparator
import com.odroid.movieready.view.components.IshaaraBottomSheetScaffold
import com.odroid.movieready.view.components.MovieChipsSection
import com.odroid.movieready.view.components.MovieLongTextSection
import com.odroid.movieready.view.components.MoviesHorizontalCardSection
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyle

@Preview
@Composable
fun PreviewMovieDetailsModal() {
    MovieDetailsModal(
        movieSuggestionModel = MovieSuggestionModel.preview
    )
}

@Destination(style = DestinationStyle.BottomSheet::class)
@Composable
fun MovieDetailsModal(movieSuggestionModel: MovieSuggestionModel) {
    val movieActors = remember {
        mutableStateOf(movieSuggestionModel.actors.getListFromStringUsingSeparator("|"))
    }
    val movieGenres = remember {
        mutableStateOf(movieSuggestionModel.genres.getListFromStringUsingSeparator("|"))
    }
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
                if (movieSuggestionModel.moviePoster.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movieSuggestionModel.moviePoster)
                            .crossfade(true)
                            .error(R.drawable.app_icon_img)
                            .build(),
                        placeholder = painterResource(R.drawable.app_icon_img),
                        contentDescription = "contentDescription",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(130.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    if (movieSuggestionModel.moviePoster.isEmpty()) {
                        Spacer(modifier = Modifier.size(28.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = movieSuggestionModel.movieTitle, style = TextStyle(
                                color = IshaaraColors.primary_app_light_text_color,
                                fontSize = 28.sp,
                                lineHeight = 32.sp,
                                fontFamily = fontBold
                            )
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        if (movieSuggestionModel.imdbRating.isNotEmpty()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_star_rating_green),
                                    contentDescription = "imdb rating"
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = movieSuggestionModel.imdbRating,
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
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (movieSuggestionModel.imdbVotes.isNotEmpty()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_people),
                                    contentDescription = "release date",
                                    tint = Color(0xFF2ECF80)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "Rated by ${movieSuggestionModel.imdbVotes}",
                                    style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (movieSuggestionModel.releaseDate.isNotEmpty()) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar_tick),
                                    contentDescription = "release date",
                                    tint = Color(0xFF2ECF80)
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = movieSuggestionModel.releaseDate,
                                    style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (movieSuggestionModel.genres.isNotEmpty()) {
                            MovieChipsSection(
                                title = "Genres",
                                items = movieGenres.value
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (movieSuggestionModel.actors.isNotEmpty()) {
                            MoviesHorizontalCardSection(
                                title = "Cast (".plus(movieActors.value.size).plus(")"),
                                items = movieActors.value
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                        if (movieSuggestionModel.summary.isNotEmpty()) {
                            MovieLongTextSection(
                                title = "Summary",
                                description = movieSuggestionModel.summary,
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
