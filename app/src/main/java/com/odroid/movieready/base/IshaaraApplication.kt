package com.odroid.movieready.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IshaaraApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: IshaaraApplication? = null
            private set
    }
}