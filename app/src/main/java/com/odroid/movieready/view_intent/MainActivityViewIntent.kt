package com.odroid.movieready.view_intent

import com.odroid.movieready.base.BaseMVIEvent
import com.odroid.movieready.base.BaseMVIViewEffect
import com.odroid.movieready.base.BaseMVIViewState

class MainActivityViewIntent {
    sealed class ViewEvent : BaseMVIEvent {
        class UpdateClicked(val number: Int): ViewEvent()
    }

    sealed class ViewState : BaseMVIViewState
    sealed class ViewEffect : BaseMVIViewEffect {
        class UpdateText(val updatedNumber: String): ViewEffect()
    }
}