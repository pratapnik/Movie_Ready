package com.odroid.movieready.util

import android.content.Context
import android.content.SharedPreferences
import com.odroid.movieready.base.IshaaraApplication

object PreferenceUtils {

    private val PREF_FILE_NAME = "ishaaraPreferences"

    private var prefs: SharedPreferences? = null

    init {
        prefs = IshaaraApplication.context?.getSharedPreferences(
            PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getBooleanPreference(key: String, defValue: Boolean): Boolean {
        return prefs?.getBoolean(key, defValue) ?: false
    }

    fun setBooleanPreference(key: String, value: Boolean) {
        prefs?.edit()?.putBoolean(key, value)?.apply()
    }

    fun getIntegerPreference(key: String, defValue: Int): Int {
        return prefs?.getInt(key, defValue) ?: -1
    }

    fun setIntegerPreference(key: String, value: Int) {
        prefs?.edit()?.putInt(key, value)?.apply()
    }
}