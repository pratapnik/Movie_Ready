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

    fun getNameInitials(name: String, numberOfInitials: Int, shouldCapitalize: Boolean): String {
        val splitName = name.split(" ")
        val nameInitials = mutableListOf<String>()

        val initialsLimit = if (splitName.size > numberOfInitials) {
            numberOfInitials
        } else {
            splitName.size
        }

        for (index in 0 until initialsLimit) {
            nameInitials.add(index, splitName.getOrNull(index)?.firstOrNull().toString())
        }

        if (shouldCapitalize) {
            return nameInitials.joinToString("").uppercase()
        }
        return nameInitials.joinToString("")
    }
}