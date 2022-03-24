package com.odroid.movieready.util

import android.content.Context
import android.media.MediaPlayer
import com.odroid.movieready.R

object CommonUtils {

    fun triggerSound(context: Context) {
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(
            context,
            R.raw.movie_generation_sound
        )
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(
                    context,
                    R.raw.movie_generation_sound
                )
            }
            mediaPlayer?.setOnCompletionListener {

            }
            mediaPlayer?.start()
        } catch (ex: java.lang.Exception) {
            if (mediaPlayer != null) {
                mediaPlayer.release()
                mediaPlayer = null
            }
        }
    }
}