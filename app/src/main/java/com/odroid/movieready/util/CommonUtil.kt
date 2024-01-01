package com.odroid.movieready.util

object CommonUtil {

    fun <T> getRandomUniqueItem(collection: List<T>): T? {
        if (collection.isEmpty()) {
            return null
        }

        val shuffledList = collection.shuffled()
        return shuffledList.getOrNull(0)
    }

}