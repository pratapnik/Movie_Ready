package com.odroid.movieready.view.fragment

import android.content.Intent
import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.viewModels
import com.google.android.material.composethemeadapter.MdcTheme
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseMVIFragmentWithEffect
import com.odroid.movieready.databinding.MovieSuggestionFragmentBinding
import com.odroid.movieready.util.ViewUtil
import com.odroid.movieready.view.activity.ExploreActivity
import com.odroid.movieready.view.views.LaunchProgressView
import com.odroid.movieready.view.views.MovieSuggestionCard
import com.odroid.movieready.view.views.TopGreeting
import com.odroid.movieready.view_intent.MovieSuggestionViewIntent
import com.odroid.movieready.view_model.MovieSuggestionViewModel

class MovieSuggestionFragment : BaseMVIFragmentWithEffect<
        MovieSuggestionViewModel,
        MovieSuggestionFragmentBinding,
        MovieSuggestionViewIntent.ViewEvent,
        MovieSuggestionViewIntent.ViewState,
        MovieSuggestionViewIntent.ViewEffect>() {

    private val movieSuggestionViewModel: MovieSuggestionViewModel by viewModels()

    private var currentMovieName = mutableStateOf("")
    private var currentPosterUrl = mutableStateOf("")
    private val movieCounter = mutableStateOf(1)

    private var lastMovieName = mutableStateOf("")
    private var lastMoviePosterUrl = mutableStateOf("")

    override val viewModel: MovieSuggestionViewModel
        get() = movieSuggestionViewModel

    override fun getLayout(): Int = R.layout.movie_suggestion_fragment

    override fun initializeViews() {
        viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.LoadMovies)
        binding.progressComposeView.setContent {
            MdcTheme {
                LaunchProgressView()
            }
        }
        binding.layoutMovieMain.btnStartGame.setOnClickListener {
            viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.UpdateClicked)
        }
        binding.layoutMovieMain.topComposeView.setContent {
            MdcTheme {
                if (currentMovieName.value.isEmpty()) {
                    TopGreeting(
                        shouldShowExploreButton = false,
                        onExploreButtonClick = this::launchExploreSection
                    )
                }
            }
        }
        binding.layoutMovieMain.movieCardComposeView.setContent {
            MdcTheme {
                MovieSuggestionCard(
                    contentDescription = "Movie Suggestion",
                    title = currentMovieName.value,
                    posterPath = currentPosterUrl.value,
                    movieCounterValue = movieCounter.value,
                    lastSuggestedMovieName = lastMovieName.value,
                    lastSuggestedMoviePosterUrl = lastMoviePosterUrl.value,
                    onNewMovieButtonClick = this::getNewMovieButtonClicked
                )
            }
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

    private fun getNewMovieButtonClicked() {
        movieCounter.value++
        lastMovieName.value = currentMovieName.value
        lastMoviePosterUrl.value = currentPosterUrl.value
        viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.UpdateClicked)
    }

    private fun launchExploreSection() {
        val exploreIntent = Intent(requireActivity(), ExploreActivity::class.java)
        startActivity(exploreIntent)
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
        binding.progressComposeView.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressComposeView.visibility = View.VISIBLE
    }

    override fun renderEffect(effect: MovieSuggestionViewIntent.ViewEffect) {
        when (effect) {
            is MovieSuggestionViewIntent.ViewEffect.UpdateText -> {
                ViewUtil.triggerSound(requireContext(), R.raw.movie_generation_sound)
                hideNoMovieView()
                currentPosterUrl.value = effect.posterPath
                currentMovieName.value = effect.movieName
            }
        }
    }

    private fun showNoMovieView() {
        binding.layoutMovieMain.btnStartGame.visibility = View.VISIBLE
        binding.layoutMovieMain.ivNoMovieIcon.visibility = View.VISIBLE
        binding.layoutMovieMain.topComposeView.visibility = View.VISIBLE
        binding.layoutMovieMain.movieCardComposeView.visibility = View.GONE
    }

    private fun hideNoMovieView() {
        binding.layoutMovieMain.movieCardComposeView.visibility = View.VISIBLE
        binding.layoutMovieMain.btnStartGame.visibility = View.GONE
        binding.layoutMovieMain.ivNoMovieIcon.visibility = View.GONE
        binding.layoutMovieMain.topComposeView.visibility = View.GONE
    }

    override fun onDestroyView() {
        requireActivity().finish()
        super.onDestroyView()
    }
}
