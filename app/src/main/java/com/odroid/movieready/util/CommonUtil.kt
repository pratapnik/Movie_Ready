package com.odroid.movieready.util

import android.util.Log
import com.odroid.movieready.model.DumbCharadesSuggestionUiModel
import java.text.NumberFormat
import java.util.Locale

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

    fun Long.toIndianRupeeWithoutDecimals(): String {
        if (this <= 0) {
            return ""
        }
        val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        return formatter.format(this).replace(".00", "")
    }

}