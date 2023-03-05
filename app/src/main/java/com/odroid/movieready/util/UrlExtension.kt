package com.odroid.movieready.util

fun String?.posterUrl(): String {
    return Constants.POSTER_BASE_URL.plus(this ?: "")
}