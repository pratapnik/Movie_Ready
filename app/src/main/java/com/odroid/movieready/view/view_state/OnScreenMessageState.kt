package com.odroid.movieready.view.view_state

data class OnScreenMessageState(
    val message: String,
    val isTriggered: Boolean
) {
    companion object {
        val default = OnScreenMessageState(
            "Something went wrong",
            false
        )
    }
}