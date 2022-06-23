package com.odroid.movieready.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.odroid.movieready.base.IshaaraApplication

object Analytics {

    private val firebaseAnalytics = IshaaraApplication.context?.let {
        FirebaseAnalytics.getInstance(
            it
        )
    }

    fun trackMovieUpdatedEvent(movieName: String) {
        val bundle = Bundle()
        bundle.putString("movie_name", movieName)
        firebaseAnalytics?.logEvent("displayed_movie_name", bundle)
    }
}