package com.odroid.movieready

import android.app.Application

class MovieReadyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: MovieReadyApplication? = null
            private set
    }
}