package com.odroid.movieready.base

import androidx.databinding.ViewDataBinding

interface BaseViewDataBindingContract<VB : ViewDataBinding> : BaseViewContract {
    fun onBindViewData(viewBinder: VB)
}