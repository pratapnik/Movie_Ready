package com.odroid.movieready.entity

data class Category(
    var categoryId: Long = 0,
    var categoryTitle: String = "",
    var priority: Long = 0,
    val url: String = "")