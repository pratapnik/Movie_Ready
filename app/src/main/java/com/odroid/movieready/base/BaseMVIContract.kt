package com.odroid.movieready.base

import androidx.databinding.ViewDataBinding

interface BaseMVIContract<
        VB : ViewDataBinding,
        VS : BaseMVIViewState>{
    fun renderState(state: VS)
}