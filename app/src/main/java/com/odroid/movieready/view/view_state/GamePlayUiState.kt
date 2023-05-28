package com.odroid.movieready.view.view_state

import com.odroid.movieready.model.MovieSuggestionModel

data class GamePlayUiState(
    val viewState: GamePlayViewState,
    val currentMovie: MovieSuggestionModel,
    val previousMovie: MovieSuggestionModel
) {
    companion object {
        val default = GamePlayUiState(
            viewState = GamePlayViewState.LOADING,
            currentMovie = MovieSuggestionModel.preview,
            previousMovie = MovieSuggestionModel(
                "", ""
            )
        )
    }
}

enum class GamePlayViewState {
    GAME_NOT_STARTED,
    LOAD_ERROR,
    GAME_STARTED,
    LOADING
}
