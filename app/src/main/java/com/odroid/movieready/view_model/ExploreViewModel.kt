package com.odroid.movieready.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odroid.movieready.entity.MovieResponse

class ExploreViewModel: ViewModel() {

    val movieClicked: MutableLiveData<MovieResponse> = MutableLiveData()

    fun movieClicked(movieResponse: MovieResponse) {
        movieClicked.value = movieResponse
    }
}