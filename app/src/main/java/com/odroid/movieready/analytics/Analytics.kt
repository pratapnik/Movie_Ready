package com.odroid.movieready.analytics

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
        bundle.putString(AnalyticsConstants.EventParam.MOVIE_NAME, movieName)
        firebaseAnalytics?.logEvent(AnalyticsConstants.EventName.DISPLAYED_MOVIE_NAME, bundle)
    }

    fun trackMovieDetailModalOpen(movieName: String, from: String) {
        val bundle = Bundle()
        bundle.putString(AnalyticsConstants.EventParam.MOVIE_NAME, movieName)
        bundle.putString(AnalyticsConstants.EventParam.FROM, from)
        firebaseAnalytics?.logEvent(AnalyticsConstants.EventName.MOVIE_DETAILS_MODAL_OPEN, bundle)
    }
}