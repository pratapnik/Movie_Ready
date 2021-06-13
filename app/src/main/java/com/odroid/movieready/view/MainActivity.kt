package com.odroid.movieready.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.google.firebase.analytics.FirebaseAnalytics
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
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun getMainLayout() = R.layout.activity_main

    override val viewModel: MainActivityViewModel
        get() = mainActivityViewModel

    override fun onViewReady() {
        super.onViewReady()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        showNoMovieView()
        viewModel.processEvent(MainActivityViewIntent.ViewEvent.LoadMovies)

        viewBinder.btnGetMovie.setOnClickListener {
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.UpdateClicked)
        }

    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {
    }

    override fun renderState(state: MainActivityViewIntent.ViewState) {
        when(state) {
            MainActivityViewIntent.ViewState.MoviesInFlight -> {
                viewBinder.layoutMovieCard.llCard.visibility = View.GONE
                viewBinder.btnGetMovie.visibility = View.GONE
                showProgressBar()
            }
            MainActivityViewIntent.ViewState.MoviesLoaded -> {
                viewBinder.layoutMovieCard.llCard.visibility = View.VISIBLE
                viewBinder.btnGetMovie.visibility = View.VISIBLE
                hideProgressBar()
            }
        }
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
                val movieText = viewBinder.layoutMovieCard.tvMovieName
                movieText.text = effect.movieName
                trackMovieUpdatedEvent(effect.movieName)
            }
        }
    }

    private fun trackMovieUpdatedEvent(movieName: String) {
        val bundle = Bundle()
        bundle.putString("movie_name", movieName)
        mFirebaseAnalytics?.logEvent("displayed_movie_name", bundle)
    }

    private fun showNoMovieView() {
        viewBinder.layoutMovieCard.llCard.setCardBackgroundColor(resources.getColor(R.color.red_color))
        viewBinder.layoutMovieCard.tvMovieName.text = resources.getString(R.string.card_description_label)
    }

    private fun hideNoMovieView() {
        viewBinder.layoutMovieCard.llCard.setCardBackgroundColor(resources.getColor(R.color.main_card_color))
    }

    private fun triggerSound() {
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(
            applicationContext,
            R.raw.movie_generation_sound
        )
        try {
            //define a new mediaplayer with your local wav file


            if (mediaPlayer == null) {//check if it's been already initialized.
                mediaPlayer = MediaPlayer.create(
                    applicationContext,
                    R.raw.movie_generation_sound
                ); // this method is expensive on cpu and memory
            }

            mediaPlayer?.setOnCompletionListener {
                Log.d(
                    "TestRing",
                    "play wav finished"
                ); //when the play completes, print a log
            }

            mediaPlayer?.start(); //start play the wav!

        } catch (ex: java.lang.Exception) {
            if (mediaPlayer != null) {

                mediaPlayer.release();// if error occurs, reinitialize the MediaPlayer

                mediaPlayer = null; // ready to be garbage collected.
            }
        }
    }
}