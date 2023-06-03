package com.odroid.movieready.analytics

object AnalyticsConstants {
    object EventValues {
        const val LAST_MOVIE_BANNER = "last_movie_banner"
        const val CURRENT_MOVIE_BANNER = "current_movie_banner"
    }
    object EventParam {
        const val MOVIE_NAME = "movie_name"
        const val FROM = "from"
    }
    object EventName {
        const val DISPLAYED_MOVIE_NAME = "displayed_movie_name"
        const val MOVIE_DETAILS_MODAL_OPEN = "movie_detail_modal_open"
    }
}