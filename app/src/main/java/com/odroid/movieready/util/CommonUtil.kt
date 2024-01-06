package com.odroid.movieready.util

import android.util.Log
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel

object CommonUtil {

    fun getRandomUniqueItem(
        collection: List<DumbCharadesSuggestionUiModel>,
        alreadySuggestedMovies: Set<Long>
    ): DumbCharadesSuggestionUiModel? {
        if (collection.isEmpty()) {
            return null
        }
        Log.d(
            "ishaara_logs",
            "alreadySuggestedMovies --> $alreadySuggestedMovies"
        )

        val shuffledList = collection.shuffled()
        return shuffledList.firstOrNull {
            it.id !in alreadySuggestedMovies
        }
    }

}