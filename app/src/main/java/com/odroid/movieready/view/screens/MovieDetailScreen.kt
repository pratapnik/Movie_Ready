package com.odroid.movieready.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.odroid.movieready.R
import com.odroid.movieready.model.MovieDetailModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.theming.fontBold
import com.odroid.movieready.theming.fontRegular
import com.odroid.movieready.theming.whiteColor
import com.odroid.movieready.util.posterUrl
import com.odroid.movieready.view.views.AppRouter
import com.odroid.movieready.view_model.MovieDetailViewModel
import com.ramcosta.composedestinations.annotation.Destination


@Preview
@Composable
fun PreviewMovieDetailsModal() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whiteColor)
    ) {
        AboutMovie(movieDetailModel = MovieDetailModel.preview) {}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun MovieDetailScreen(
    appRouter: AppRouter,
    bottomSheetState: ModalBottomSheetState,
    movieId: Long,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val movieDetails by viewModel.movieDetails.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getMovieDetails(movieId = movieId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whiteColor)
    ) {
        AboutMovie(movieDetails) {
            appRouter.navigateUp()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun AboutMovie(movieDetailModel: MovieDetailModel, backPressed: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetailModel.posterPath.posterUrl())
                .crossfade(true)
                .error(R.drawable.app_icon_img)
                .build(),
            placeholder = painterResource(R.drawable.app_icon_img),
            contentDescription = "contentDescription",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6F)
        )
        Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = "",
            modifier = Modifier
                .padding(24.dp)
                .size(32.dp)
                .clickable {
                    backPressed.invoke()
                })

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier.weight(1F)) {

                }
                Box(
                    modifier = Modifier
                        .weight(1.7F)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0x00000000),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.95F),
                                    Color.Black.copy(alpha = 0.995F),
                                    Color.Black.copy(alpha = 0.995F)
                                )
                            )
                        )
                        .padding(horizontal = 48.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = movieDetailModel.title, style = TextStyle(
                                color = IshaaraColors.primary_app_light_text_color,
                                fontSize = 24.sp,
                                lineHeight = 28.sp,
                                fontFamily = fontBold
                            ),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = movieDetailModel.releaseDate, style = TextStyle(
                                    color = IshaaraColors.primary_app_light_text_color,
                                    fontSize = 16.sp,
                                    fontFamily = fontRegular
                                )
                            )
                            if (movieDetailModel.voteAverage.isNotEmpty()) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_rating),
                                        contentDescription = "rating",
                                        modifier = Modifier
                                            .padding(end = 4.dp)
                                            .size(16.dp)
                                    )
                                    Text(
                                        text = movieDetailModel.voteAverage, style = TextStyle(
                                            color = IshaaraColors.primary_app_light_text_color,
                                            fontSize = 16.sp,
                                            fontFamily = fontRegular
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        Text(
                            text = movieDetailModel.overview, style = TextStyle(
                                color = IshaaraColors.primary_app_light_text_color,
                                fontSize = 16.sp,
                                fontFamily = fontRegular
                            ),
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic
                        )
                        if (movieDetailModel.genres.isNotEmpty()) {
                            Spacer(modifier = Modifier.size(32.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Genres: ", style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontBold
                                    ),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = movieDetailModel.genres, style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    ),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        if (movieDetailModel.runtime.isNotEmpty()) {
                            Spacer(modifier = Modifier.size(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Runtime: ", style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontBold
                                    ),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = movieDetailModel.runtime, style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        if (movieDetailModel.budget.isNotEmpty()) {
                            Spacer(modifier = Modifier.size(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Budget: ", style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontBold
                                    ),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = movieDetailModel.budget, style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        if (movieDetailModel.revenue.isNotEmpty()) {
                            Spacer(modifier = Modifier.size(12.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Revenue: ", style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontBold
                                    ),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = movieDetailModel.revenue, style = TextStyle(
                                        color = IshaaraColors.primary_app_light_text_color,
                                        fontSize = 16.sp,
                                        fontFamily = fontRegular
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

            }
        }


    }

}
