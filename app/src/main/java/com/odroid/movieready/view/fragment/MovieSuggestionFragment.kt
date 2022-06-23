package com.odroid.movieready.view.fragment

import android.media.MediaPlayer
import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.viewModels
import com.google.android.material.composethemeadapter.MdcTheme
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseMVIFragmentWithEffect
import com.odroid.movieready.databinding.MovieSuggestionFragmentBinding
import com.odroid.movieready.util.Constants
import com.odroid.movieready.util.DateUtil
import com.odroid.movieready.view.layout.MovieSuggestionCard
import com.odroid.movieready.view.layout.TopGreeting
import com.odroid.movieready.view_intent.MovieSuggestionViewIntent
import com.odroid.movieready.view_model.MovieSuggestionViewModel
import java.text.SimpleDateFormat
import java.util.*

class MovieSuggestionFragment : BaseMVIFragmentWithEffect<
        MovieSuggestionViewModel,
        MovieSuggestionFragmentBinding,
        MovieSuggestionViewIntent.ViewEvent,
        MovieSuggestionViewIntent.ViewState,
        MovieSuggestionViewIntent.ViewEffect>() {

    private val movieSuggestionViewModel: MovieSuggestionViewModel by viewModels()

    private var mutableDate = mutableStateOf("")
    private var mutableDay = mutableStateOf("")
    private var mutableMovieName = mutableStateOf("")
    private var posterUrl = mutableStateOf("")

    override val viewModel: MovieSuggestionViewModel
        get() = movieSuggestionViewModel

    override fun getLayout(): Int = R.layout.movie_suggestion_fragment

    override fun initializeViews() {
        setDayAndDate()
        viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.LoadMovies)
        binding.layoutMovieMain.btnGetMovie.setOnClickListener {
            viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.UpdateClicked)
        }
        binding.layoutMovieMain.btnStartGame.setOnClickListener {
            viewModel.processEvent(MovieSuggestionViewIntent.ViewEvent.UpdateClicked)
        }
        binding.layoutMovieMain.topComposeView.setContent {
            MdcTheme {
                TopGreeting(mutableDate.value, mutableDay.value)
            }
        }
        binding.layoutMovieMain.movieCardComposeView.setContent {
            MdcTheme {
                MovieSuggestionCard(
                    contentDescription = "Movie Suggestion",
                    title = mutableMovieName.value,
                    posterPath = posterUrl.value
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
                triggerSound()
                hideNoMovieView()
                posterUrl.value = effect.posterPath
                mutableMovieName.value = effect.movieName
            }
        }
    }

    private fun setDayAndDate() {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat(Constants.NORMAL_DATE_FORMAT, Locale.getDefault())
        val formattedDate = df.format(c)
        val day = DateUtil.getFormattedDate(
            formattedDate,
            Constants.NORMAL_DATE_FORMAT,
            Constants.ONLY_DAY_OF_WEEK_FORMAT
        )
        mutableDate.value = formattedDate
        mutableDay.value = DateUtil.getDayTextForHomeScreen(day)
    }

    private fun showNoMovieView() {
        binding.layoutMovieMain.btnStartGame.visibility = View.VISIBLE
        binding.layoutMovieMain.ivNoMovieIcon.visibility = View.VISIBLE
        binding.layoutMovieMain.movieSuggestionGroupView.visibility = View.GONE
    }

    private fun hideNoMovieView() {
        binding.layoutMovieMain.movieSuggestionGroupView.visibility = View.VISIBLE
        binding.layoutMovieMain.btnStartGame.visibility = View.GONE
        binding.layoutMovieMain.ivNoMovieIcon.visibility = View.GONE
    }

    private fun triggerSound() {
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(
            requireContext(),
            R.raw.movie_generation_sound
        )
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(
                    requireContext(),
                    R.raw.movie_generation_sound
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

    override fun onDestroyView() {
        requireActivity().finish()
        super.onDestroyView()
    }
}
