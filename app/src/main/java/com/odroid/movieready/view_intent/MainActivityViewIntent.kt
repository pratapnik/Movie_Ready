package com.odroid.movieready.view_intent

import com.odroid.movieready.base.BaseMVIEvent
import com.odroid.movieready.base.BaseMVIViewEffect
import com.odroid.movieready.base.BaseMVIViewState

class MainActivityViewIntent {
    sealed class ViewEvent : BaseMVIEvent {
        object UpdateClicked : ViewEvent()
        object LoadMovies: ViewEvent()
    }

    sealed class ViewState : BaseMVIViewState {
        object MoviesLoaded: ViewState()
        object MoviesInFlight: ViewState()
        object MoviesLoadingFailed: ViewState()
    }

    sealed class ViewEffect : BaseMVIViewEffect {
        class UpdateText(val movieName: String) : ViewEffect()
    }
}