package com.odroid.movieready.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.facebook.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseMVIActivityWithEffect
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.view_intent.MainActivityViewIntent
import com.odroid.movieready.view_model.MainActivityViewModel
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.createBalloon
import kotlinx.coroutines.coroutineScope
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

    private val TAG = "FB_ADS"
    private var shouldShowButtonTooltip = true

    private lateinit var adView: AdView
    private var interstitialAdCount = 1
    private lateinit var interstitialAd: InterstitialAd

    override fun getMainLayout() = R.layout.activity_main

    override val viewModel: MainActivityViewModel
        get() = mainActivityViewModel

    override fun onViewReady() {
        super.onViewReady()
        AudienceNetworkAds.initialize(this)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        adView = AdView(this, "523149145395752_523153555395311", AdSize.BANNER_HEIGHT_50)
        val adContainer = findViewById<View>(R.id.banner_container) as LinearLayout
        adContainer.addView(adView)
        val adListener: AdListener = object : AdListener {
            override fun onError(ad: Ad, adError: AdError) {
                // Ad error callback
            }

            override fun onAdLoaded(ad: Ad) {
                // Ad loaded callback
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
            }
        }
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build())

        interstitialAd = InterstitialAd(this, "523149145395752_523176515393015")

        viewModel.processEvent(MainActivityViewIntent.ViewEvent.LoadMovies)

        viewBinder.btnGetMovie.setOnClickListener {
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.UpdateClicked)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (adView != null) {
            adView.destroy()
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {
        //Nothing here
    }

    override fun renderState(state: MainActivityViewIntent.ViewState) {
        when (state) {
            MainActivityViewIntent.ViewState.MoviesInFlight -> {
                viewBinder.layoutMovieCard.llCard.visibility = View.GONE
                viewBinder.btnGetMovie.visibility = View.GONE
                viewBinder.tvTitle.visibility = View.GONE
                showProgressBar()
                hideErrorView()
            }
            MainActivityViewIntent.ViewState.MoviesLoaded -> {
                viewBinder.layoutMovieCard.llCard.visibility = View.VISIBLE
                viewBinder.btnGetMovie.visibility = View.VISIBLE
                viewBinder.tvTitle.visibility = View.VISIBLE
                showNoMovieView()
                hideProgressBar()
                hideErrorView()
                showTooltipOnButton()
            }
            MainActivityViewIntent.ViewState.MoviesLoadingFailed -> {
                showErrorView()
                hideProgressBar()
                viewBinder.layoutMovieCard.llCard.visibility = View.GONE
                viewBinder.btnGetMovie.visibility = View.GONE
                viewBinder.tvTitle.visibility = View.GONE
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
                showInterstitialAd()
                interstitialAdCount++
                val movieText = viewBinder.layoutMovieCard.tvMovieName
                movieText.text = effect.movieName
                trackMovieUpdatedEvent(effect.movieName)
            }
        }
    }

    private fun showInterstitialAd() {
        if (interstitialAdCount % 5 == 0) {
            val interstitialAdListener: InterstitialAdListener = object : InterstitialAdListener {
                override fun onInterstitialDisplayed(ad: Ad) {
                    Log.e(TAG, "Interstitial ad displayed.")
                }

                override fun onInterstitialDismissed(ad: Ad) {
                    Log.e(TAG, "Interstitial ad dismissed.")
                }

                override fun onError(ad: Ad, adError: AdError) {
                    Log.e(TAG, "Interstitial ad failed to load: " + adError.errorMessage)
                }

                override fun onAdLoaded(ad: Ad) {
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!")
                    interstitialAd.show()
                }

                override fun onAdClicked(ad: Ad) {
                    Log.d(TAG, "Interstitial ad clicked!")
                }

                override fun onLoggingImpression(ad: Ad) {
                    Log.d(TAG, "Interstitial ad impression logged!")
                }
            }
            interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                    .withAdListener(interstitialAdListener)
                    .build()
            )
        }
    }

    private fun trackMovieUpdatedEvent(movieName: String) {
        val bundle = Bundle()
        bundle.putString("movie_name", movieName)
        mFirebaseAnalytics?.logEvent("displayed_movie_name", bundle)
    }

    private fun showNoMovieView() {
        viewBinder.layoutMovieCard.llCard.setCardBackgroundColor(resources.getColor(R.color.red_color))
        viewBinder.layoutMovieCard.tvMovieName.text =
            resources.getString(R.string.card_description_label)
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

    private fun showTooltipOnButton() {
        if(shouldShowButtonTooltip) {
            lifecycleScope.launch {
                delay(400)
                val balloon = createBalloon(baseContext) {
                    setArrowSize(9)
                    setWidthRatio(0.7f)
                    setHeight(50)
                    setArrowPosition(0.5f)
                    setCornerRadius(8f)
                    setAlpha(0.9f)
                    setText(resources.getString(R.string.tooltip_label))
                    setTextColorResource(R.color.main_card_color)
                    setTextSize(16F)
                    setBackgroundColorResource(R.color.tooltip_color)
                    onBalloonClickListener?.let { setOnBalloonClickListener(it) }
                    setBalloonAnimation(BalloonAnimation.OVERSHOOT)
                    setLifecycleOwner(lifecycleOwner)
                }
                balloon.showAlignTop(viewBinder.btnGetMovie)
                shouldShowButtonTooltip = false
            }
        }
    }
}