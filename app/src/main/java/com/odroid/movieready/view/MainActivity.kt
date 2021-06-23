package com.odroid.movieready.view

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
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

    private lateinit var mAdView: AdView
    private var interstitialAdCount = 1
    private var mInterstitialAd: InterstitialAd? = null

    override fun getMainLayout() = com.odroid.movieready.R.layout.activity_main

    override val viewModel: MainActivityViewModel
        get() = mainActivityViewModel

    override fun onViewReady() {
        super.onViewReady()
        MobileAds.initialize(this)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        mAdView = findViewById(com.odroid.movieready.R.id.banner_container)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }

        var interAdRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-1519112618002676/9466278282", interAdRequest,
            object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
            }

            override fun onAdShowedFullScreenContent() {
                mInterstitialAd = null;
            }
        }

        viewModel.processEvent(MainActivityViewIntent.ViewEvent.LoadMovies)

        viewBinder.btnGetMovie.setOnClickListener {
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.UpdateClicked)
        }
        viewModel.processEvent(MainActivityViewIntent.ViewEvent.CheckPosterSwitch(this))

        registerSwitchChangeListener()
    }

    private fun registerSwitchChangeListener() {
        viewBinder.switchPoster.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.processEvent(MainActivityViewIntent.ViewEvent.PosterSwitchChanged(isChecked,
                this))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mAdView != null) {
            mAdView.destroy()
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
                animateCardView()
                val movieText = viewBinder.layoutMovieCard.tvMovieName
                movieText.text = effect.movieName
                trackMovieUpdatedEvent(effect.movieName)
            }
            is MainActivityViewIntent.ViewEffect.UpdatePosterSwitch -> {
                viewBinder.switchPoster.isChecked = effect.isChecked
            }
        }
    }

    private fun animateCardView() {
        val animation: Animation = AnimationUtils.loadAnimation(this, com.odroid.movieready.R.anim.zoom_in_anim)
        viewBinder.layoutMovieCard.llCard.startAnimation(animation);
    }

    private fun showInterstitialAd() {
        if (interstitialAdCount % 5 == 0) {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
            }
        }
    }

    private fun trackMovieUpdatedEvent(movieName: String) {
        val bundle = Bundle()
        bundle.putString("movie_name", movieName)
        mFirebaseAnalytics?.logEvent("displayed_movie_name", bundle)
    }

    private fun showNoMovieView() {
        viewBinder.layoutMovieCard.llCard.setCardBackgroundColor(resources.getColor(com.odroid.movieready.R.color.primary_error_color))
        viewBinder.layoutMovieCard.tvMovieName.text =
            resources.getString(com.odroid.movieready.R.string.card_description_label)
    }

    private fun hideNoMovieView() {
        viewBinder.layoutMovieCard.llCard.setCardBackgroundColor(resources.getColor(com.odroid.movieready.R.color.main_card_color))
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
                balloon.showAlignTop(viewBinder.btnGetMovie)
                shouldShowButtonTooltip = false
            }
        }
    }
}