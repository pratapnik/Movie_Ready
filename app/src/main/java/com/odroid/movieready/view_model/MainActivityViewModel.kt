package com.odroid.movieready.view_model

import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.view_intent.MainActivityViewIntent

class MainActivityViewModel : BaseMVIViewModelWithEffect<
        MainActivityViewIntent.ViewEvent,
        MainActivityViewIntent.ViewState,
        MainActivityViewIntent.ViewEffect>() {

    override fun processEvent(event: MainActivityViewIntent.ViewEvent) {
        when (event) {
            is MainActivityViewIntent.ViewEvent.UpdateClicked -> {
                viewEffect = MainActivityViewIntent.ViewEffect.UpdateText
            }
        }
    }
}