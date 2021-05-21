package com.odroid.movieready.base

interface BaseViewContract {
    fun getMainLayout(): Int
    fun onViewReady()
    fun onViewDestroyed()
}