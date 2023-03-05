package com.odroid.movieready.util

import android.content.Context
import android.media.MediaPlayer

object ViewUtil {

    fun triggerSound(context: Context, soundResource: Int) {
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(
            context,
            soundResource
        )
        try {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(
                    context,
                    soundResource
                )
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