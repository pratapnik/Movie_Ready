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
                val updatedNumber = (event.number + 1).toString()
                viewEffect = MainActivityViewIntent.ViewEffect.UpdateText(updatedNumber)
            }
        }
    }
}