package com.odroid.movieready.view.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import coil.load
import com.google.firebase.analytics.FirebaseAnalytics
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseMVIFragmentWithEffect
import com.odroid.movieready.databinding.MovieSuggestionFragmentBinding
import com.odroid.movieready.util.CommonUtils
import com.odroid.movieready.view_intent.MovieSuggestionViewIntent
import com.odroid.movieready.view_model.MovieSuggestionViewModel

class MovieSuggestionFragment : BaseMVIFragmentWithEffect<
        MovieSuggestionViewModel,
        MovieSuggestionFragmentBinding,
        MovieSuggestionViewIntent.ViewEvent,
        MovieSuggestionViewIntent.ViewState,
        MovieSuggestionViewIntent.ViewEffect>() {

    private val movieSuggestionViewModel: MovieSuggestionViewModel by viewModels()

    private var mFirebaseAnalytics: FirebaseAnalytics? = null


    override val viewModel: MovieSuggestionViewModel
        get() = movieSuggestionViewModel

    override fun getLayout(): Int = R.layout.movie_suggestion_fragment

    override fun initializeViews() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.LoadMovies)
        binding.layoutMovieMain.btnGetMovie.setOnClickListener {
            viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.UpdateClicked)
        }
        viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.CheckPosterSwitch)
        registerSwitchChangeListener()
    }

    private fun registerSwitchChangeListener() {
        binding.layoutMovieMain.switchPoster.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.PosterSwitchChanged(isChecked))
        }
    }

    override fun renderState(state: MovieSuggestionViewIntent.ViewState) {
        when (state) {
            MovieSuggestionViewIntent.ViewState.MoviesInFlight -> {
                binding.layoutMovieMain.clMovieLayoutMain.visibility = View.GONE
                showProgressBar()
                hideErrorView()
            }
            MovieSuggestionViewIntent.ViewState.MoviesLoaded -> {
                binding.layoutMovieMain.clMovieLayoutMain.visibility = View.VISIBLE
                showNoMovieView()
                hideProgressBar()
                hideErrorView()
            }
            MovieSuggestionViewIntent.ViewState.MoviesLoadingFailed -> {
                showErrorView()
                hideProgressBar()
                binding.layoutMovieMain.clMovieLayoutMain.visibility = View.GONE
            }
        }
    }

    private fun showErrorView() {
        binding.errorView.btnRetry.setOnClickListener {
            viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.LoadMovies)
        }
        binding.errorView.clError.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        binding.errorView.clError.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun renderEffect(effect: MovieSuggestionViewIntent.ViewEffect) {
        when (effect) {
            is MovieSuggestionViewIntent.ViewEffect.UpdateText -> {
                CommonUtils.triggerSound(requireContext())
                hideNoMovieView()
                animateCardView()
                loadPoster(effect.posterPath)
                val movieText = binding.layoutMovieMain.layoutMovieCard.tvMovieName
                movieText.text = effect.movieName
                trackMovieUpdatedEvent(effect.movieName)
            }
            is MovieSuggestionViewIntent.ViewEffect.UpdatePosterSwitch -> {
                binding.layoutMovieMain.switchPoster.isChecked = effect.isChecked
            }
            MovieSuggestionViewIntent.ViewEffect.UpdatePosterVisibility -> setPosterVisibility()
        }
    }

    private fun setPosterVisibility() {
        if (binding.layoutMovieMain.switchPoster.isChecked)
            binding.layoutMovieMain.layoutMovieCard.ivPoster.visibility = View.VISIBLE
        else
            binding.layoutMovieMain.layoutMovieCard.ivPoster.visibility = View.GONE
    }

    private fun loadPoster(posterPath: String) {
        setPosterVisibility()
        if (posterPath.isNotEmpty()) {
            binding.layoutMovieMain.layoutMovieCard.ivPoster.load(posterPath) {
                error(R.drawable.ic_unavailable)
                crossfade(true)
            }
        } else {
            binding.layoutMovieMain.layoutMovieCard.ivPoster.load(R.drawable.ic_unavailable)
        }
    }

    private fun animateCardView() {
        val animation: Animation =
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.zoom_in_anim
            )
        binding.layoutMovieMain.layoutMovieCard.llCard.startAnimation(animation)
    }

    private fun trackMovieUpdatedEvent(movieName: String) {
        val bundle = Bundle()
        bundle.putString("movie_name", movieName)
        mFirebaseAnalytics?.logEvent("displayed_movie_name", bundle)
    }

    private fun showNoMovieView() {
        binding.layoutMovieMain.layoutMovieCard.llCard.setCardBackgroundColor(
            resources.getColor(
                R.color.primary_error_color
            )
        )
        binding.layoutMovieMain.layoutMovieCard.tvMovieName.text =
            resources.getString(R.string.card_description_label)
    }

    private fun hideNoMovieView() {
        binding.layoutMovieMain.layoutMovieCard.llCard.setCardBackgroundColor(
            resources.getColor(
                R.color.main_card_color
            )
        )
    }


    override fun onDestroyView() {
        requireActivity().finish()
        super.onDestroyView()
    }
}