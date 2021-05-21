package com.odroid.movieready.view

import androidx.activity.viewModels
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseMVIActivityWithEffect
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.view_intent.MainActivityViewIntent
import com.odroid.movieready.view_model.MainActivityViewModel

class MainActivity : BaseMVIActivityWithEffect<
        MainActivityViewModel,
        ActivityMainBinding,
        MainActivityViewIntent.ViewEvent,
        MainActivityViewIntent.ViewState,
        MainActivityViewIntent.ViewEffect>() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private var screenNumber = 0

    override fun getMainLayout() = R.layout.activity_main

    override val viewModel: MainActivityViewModel
        get() = mainActivityViewModel

    override fun onViewReady() {
        super.onViewReady()
        viewBinder.btnChangeText.setOnClickListener {
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.UpdateClicked(screenNumber))
        }
    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {
    }

    override fun renderState(state: MainActivityViewIntent.ViewState) {
    }

    override fun renderEffect(effect: MainActivityViewIntent.ViewEffect) {
        when (effect) {
            is MainActivityViewIntent.ViewEffect.UpdateText -> updateText(effect.updatedNumber)
        }
    }

    private fun updateText(updatedNumber: String) {
        screenNumber = updatedNumber.toInt()
        viewBinder.tvText.text = "After adding 1 to the number: ".plus(updatedNumber)
    }
}