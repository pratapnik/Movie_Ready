package com.odroid.movieready.view.view_state

import com.odroid.movieready.model.DumbCharadesSuggestionUiModel

data class GamePlayUiState(
    val viewState: GamePlayViewState,
    val currentMovie: DumbCharadesSuggestionUiModel,
    val previousMovie: DumbCharadesSuggestionUiModel
) {
    companion object {
        val default = GamePlayUiState(
            viewState = GamePlayViewState.GAME_NOT_STARTED,
            currentMovie = DumbCharadesSuggestionUiModel.empty,
            previousMovie = DumbCharadesSuggestionUiModel.empty
        )
    }
}

enum class GamePlayViewState {
    GAME_NOT_STARTED,
    LOAD_ERROR,
    GAME_STARTED,
    LOADING,
    MOVIE_DETAIL
}
