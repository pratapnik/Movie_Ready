package com.odroid.movieready.util

import android.content.Context
import android.content.SharedPreferences
import com.odroid.movieready.base.IshaaraApplication

object PreferenceUtils {

    private val PREF_FILE_NAME = "XmppPref"

    private var prefs: SharedPreferences? = null

    init {
        prefs = IshaaraApplication.context?.getSharedPreferences(
            PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    private fun getBooleanPreference(key: String, defValue: Boolean): Boolean {
        return prefs?.getBoolean(key, defValue) ?: false
    }

    private fun setBooleanPreference(key: String, value: Boolean) {
        prefs?.edit()?.putBoolean(key, value)?.apply()
    }
}