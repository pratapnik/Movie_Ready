package com.odroid.movieready.util

import android.content.Context
import android.content.SharedPreferences
import com.odroid.movieready.base.IshaaraApplication

object PreferenceUtils {
    private val PREF_FILE_NAME = "XmppPref"

    private val IS_POSTER_ON = "is_poster_on"

    private var prefs: SharedPreferences? = null

    init {
        prefs = IshaaraApplication.context?.getSharedPreferences(
            PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun setPosterEnabled(isPosterEnabled: Boolean) {
        setBooleanPreference(IS_POSTER_ON, isPosterEnabled)
    }

    fun isPosterEnabled(): Boolean {
        return getBooleanPreference(IS_POSTER_ON, true)
    }

    private fun getBooleanPreference(key: String, defValue: Boolean): Boolean {
        return prefs?.getBoolean(key, defValue) ?: false
    }

    private fun setBooleanPreference(key: String, value: Boolean) {
        prefs?.edit()?.putBoolean(key, value)?.apply()
    }
}