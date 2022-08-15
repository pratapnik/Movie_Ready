package com.odroid.movieready.util

import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class DummyDestinationsNavigator: DestinationsNavigator {
    override fun clearBackStack(route: String): Boolean {
        return false
    }

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        builder: NavOptionsBuilder.() -> Unit
    ) {
    }

    override fun navigateUp(): Boolean {
        return false
    }

    override fun popBackStack(): Boolean {
        return false
    }

    override fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean): Boolean {
        return false
    }
}