package com.odroid.movieready.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.odroid.movieready.theming.IshaaraColors
import com.odroid.movieready.util.ViewUtil
import com.odroid.movieready.view.sideEffect.OneShotEffect
import com.odroid.movieready.view.view_state.GamePlayViewState
import com.odroid.movieready.view.views.AppRouter
import com.odroid.movieready.view.views.ErrorView
import com.odroid.movieready.view.views.GamePlayOnboardingView
import com.odroid.movieready.view.views.LaunchProgressView
import com.odroid.movieready.view.views.MovieSuggestionCard
import com.odroid.movieready.view_model.GamePlayViewModel
import com.ramcosta.composedestinations.annotation.Destination


@OptIn(ExperimentalMaterialApi::class)
@Destination(start = true)
@Composable
fun GamePlayStartScreen(
    appRouter: AppRouter,
    bottomSheetState: ModalBottomSheetState,
    viewModel: GamePlayViewModel = hiltViewModel()
) {
    val gamePlayUiState by viewModel.gamePlayUiState.collectAsState()
    val movieCounter = remember {
        mutableIntStateOf(1)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = gamePlayUiState.currentMovie.movieTitle) {
        if (gamePlayUiState.currentMovie.movieTitle.isNotEmpty()) {
            ViewUtil.triggerSound(context, com.odroid.movieready.R.raw.movie_generation_sound)
        }
    }

    OneShotEffect {
        viewModel.getAllMov()
    }

    when (gamePlayUiState.viewState) {
        GamePlayViewState.GAME_NOT_STARTED -> {
            GamePlayOnboardingView(onStartClick = {
                viewModel.startGame()
            })
        }

        GamePlayViewState.LOAD_ERROR -> {
            ErrorView(tryAgain = {
                viewModel.getAllMov()
            })
        }

        GamePlayViewState.GAME_STARTED -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = IshaaraColors.background_color_FFFFFF),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MovieSuggestionCard(
                    contentDescription = "Movie Suggestion",
                    title = gamePlayUiState.currentMovie.movieTitle,
                    posterPath = gamePlayUiState.currentMovie.moviePoster,
                    movieCounterValue = movieCounter.intValue,
                    lastSuggestedMovieName = gamePlayUiState.previousMovie.movieTitle,
                    lastSuggestedMoviePosterUrl = gamePlayUiState.previousMovie.moviePoster,
                    onNewMovieButtonClick = {
                        movieCounter.intValue++
                        viewModel.newMovieClicked()
                    },
                    openMovieDetails = {
                    }
                )
            }
        }

        GamePlayViewState.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                LaunchProgressView()
            }
        }
    }
}