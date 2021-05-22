package com.odroid.movieready.view_intent

import com.odroid.movieready.base.BaseMVIEvent
import com.odroid.movieready.base.BaseMVIViewEffect
import com.odroid.movieready.base.BaseMVIViewState

class MainActivityViewIntent {
    sealed class ViewEvent : BaseMVIEvent {
        object UpdateClicked : ViewEvent()
    }

    sealed class ViewState : BaseMVIViewState
    sealed class ViewEffect : BaseMVIViewEffect {
        object UpdateText : ViewEffect()
    }
}