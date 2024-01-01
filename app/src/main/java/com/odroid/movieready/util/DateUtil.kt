package com.odroid.movieready.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getFormattedDate(
        fromDate: String,
        fromPattern: String,
        toPattern: String
    ): String {
        val parser = SimpleDateFormat(fromPattern, Locale.ENGLISH)
        val formatter = SimpleDateFormat(toPattern, Locale.ENGLISH)
        return formatter.format(parser.parse(fromDate))
    }

    fun getDayTextForHomeScreen(day: String): String {
        return when(day) {
            "Sunday", "Friday", "Saturday" -> "yayy! it's $day"
            else -> day
        }
    }


    fun convertDateFormat(inputDate: String): String {
        inputDate.ifEmpty {
            return "NA"
        }
        // Parse input date
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(inputDate)

        // Format output date
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return outputFormat.format(date)
    }
}