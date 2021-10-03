package com.odroid.movieready.view

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.firebase.analytics.FirebaseAnalytics
import com.odroid.movieready.base.BaseMVIActivityWithEffect
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.view_intent.MainActivityViewIntent
import com.odroid.movieready.view_model.MainActivityViewModel
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseMVIActivityWithEffect<
        MainActivityViewModel,
        ActivityMainBinding,
        MainActivityViewIntent.ViewEvent,
        MainActivityViewIntent.ViewState,
        MainActivityViewIntent.ViewEffect>() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    private var shouldShowButtonTooltip = true

    override fun getMainLayout() = com.odroid.movieready.R.layout.activity_main

    override val viewModel: MainActivityViewModel
        get() = mainActivityViewModel

    override fun onViewReady() {
        super.onViewReady()
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        viewModel.processEvent(MainActivityViewIntent.ViewEvent.LoadMovies)

        viewBinder.layoutMovieMain.btnGetMovie.setOnClickListener {
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.UpdateClicked)
        }
        viewModel.processEvent(MainActivityViewIntent.ViewEvent.CheckPosterSwitch)

        registerSwitchChangeListener()
    }

    private fun registerSwitchChangeListener() {
        viewBinder.layoutMovieMain.switchPoster.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.PosterSwitchChanged(isChecked))
        }
    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {
        //Nothing here
    }

    override fun renderState(state: MainActivityViewIntent.ViewState) {
        when (state) {
            MainActivityViewIntent.ViewState.MoviesInFlight -> {
                viewBinder.layoutMovieMain.clMovieLayoutMain.visibility = View.GONE
                showProgressBar()
                hideErrorView()
            }
            MainActivityViewIntent.ViewState.MoviesLoaded -> {
                viewBinder.layoutMovieMain.clMovieLayoutMain.visibility = View.VISIBLE
                showNoMovieView()
                hideProgressBar()
                hideErrorView()
                showTooltipOnButton()
            }
            MainActivityViewIntent.ViewState.MoviesLoadingFailed -> {
                showErrorView()
                hideProgressBar()
                viewBinder.layoutMovieMain.clMovieLayoutMain.visibility = View.GONE
            }
        }
    }

    private fun showErrorView() {
        viewBinder.errorView.btnRetry.setOnClickListener {
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.LoadMovies)
        }
        viewBinder.errorView.clError.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        viewBinder.errorView.clError.visibility = View.GONE
    }

    private fun hideProgressBar() {
        viewBinder.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        viewBinder.progressBar.visibility = View.VISIBLE
    }

    override fun renderEffect(effect: MainActivityViewIntent.ViewEffect) {
        when (effect) {
            is MainActivityViewIntent.ViewEffect.UpdateText -> {
                triggerSound()
                hideNoMovieView()
                animateCardView()
                loadPoster(effect.posterPath)
                val movieText = viewBinder.layoutMovieMain.layoutMovieCard.tvMovieName
                movieText.text = effect.movieName
                trackMovieUpdatedEvent(effect.movieName)
            }
            is MainActivityViewIntent.ViewEffect.UpdatePosterSwitch -> {
                viewBinder.layoutMovieMain.switchPoster.isChecked = effect.isChecked
            }
            MainActivityViewIntent.ViewEffect.UpdatePosterVisibility -> setPosterVisibility()
        }
    }

    private fun setPosterVisibility() {
        if (viewBinder.layoutMovieMain.switchPoster.isChecked)
            viewBinder.layoutMovieMain.layoutMovieCard.ivPoster.visibility = View.VISIBLE
        else
            viewBinder.layoutMovieMain.layoutMovieCard.ivPoster.visibility = View.GONE
    }

    private fun loadPoster(posterPath: String) {
        setPosterVisibility()
        if (posterPath.isNotEmpty()) {
            viewBinder.layoutMovieMain.layoutMovieCard.ivPoster.load(posterPath) {
                error(com.odroid.movieready.R.drawable.ic_unavailable)
                crossfade(true)
            }
        } else {
            viewBinder.layoutMovieMain.layoutMovieCard.ivPoster.load(com.odroid.movieready.R.drawable.ic_unavailable)
        }
    }

    private fun animateCardView() {
        val animation: Animation =
            AnimationUtils.loadAnimation(this, com.odroid.movieready.R.anim.zoom_in_anim)
        viewBinder.layoutMovieMain.layoutMovieCard.llCard.startAnimation(animation)
    }

    private fun trackMovieUpdatedEvent(movieName: String) {
        val bundle = Bundle()
        bundle.putString("movie_name", movieName)
        mFirebaseAnalytics?.logEvent("displayed_movie_name", bundle)
    }

    private fun showNoMovieView() {
        viewBinder.layoutMovieMain.layoutMovieCard.llCard.setCardBackgroundColor(
            resources.getColor(
                com.odroid.movieready.R.color.primary_error_color
            )
        )
        viewBinder.layoutMovieMain.layoutMovieCard.tvMovieName.text =
            resources.getString(com.odroid.movieready.R.string.card_description_label)
    }

    private fun hideNoMovieView() {
        viewBinder.layoutMovieMain.layoutMovieCard.llCard.setCardBackgroundColor(
            resources.getColor(
                com.odroid.movieready.R.color.main_card_color
            )
        )
    }

    private fun triggerSound() {
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(
            applicationContext,
            com.odroid.movieready.R.raw.movie_generation_sound
        )
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(
                    applicationContext,
                    com.odroid.movieready.R.raw.movie_generation_sound
                )
            }

            mediaPlayer?.setOnCompletionListener {

            }

            mediaPlayer?.start()

        } catch (ex: java.lang.Exception) {
            if (mediaPlayer != null) {

                mediaPlayer.release()

                mediaPlayer = null
            }
        }
    }

    private fun showTooltipOnButton() {
        if (shouldShowButtonTooltip) {
            lifecycleScope.launch {
                delay(400)
                val balloon = createBalloon(baseContext) {
                    setArrowSize(9)
                    setWidthRatio(0.7f)
                    setHeight(50)
                    setArrowPosition(0.5f)
                    setCornerRadius(8f)
                    setAlpha(0.9f)
                    setText(resources.getString(com.odroid.movieready.R.string.tooltip_label))
                    setTextColorResource(com.odroid.movieready.R.color.main_card_color)
                    setTextSize(16F)
                    setBackgroundColorResource(com.odroid.movieready.R.color.tooltip_color)
                    onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                    setBalloonAnimation(BalloonAnimation.OVERSHOOT)
                    setLifecycleOwner(lifecycleOwner)
                }
                balloon.showAlignTop(viewBinder.layoutMovieMain.btnGetMovie)
                shouldShowButtonTooltip = false
            }
        }
    }
}