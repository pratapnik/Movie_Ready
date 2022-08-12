package com.odroid.movieready.view_intent

import com.odroid.movieready.entity.SourceType

data class EntertainmentCategory(
    var title: String = "",
    var icon: Int = 0,
    val category: SourceType
)
