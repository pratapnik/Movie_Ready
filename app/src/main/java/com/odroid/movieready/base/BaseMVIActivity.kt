package com.odroid.movieready.base

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding

abstract class BaseMVIActivity<
        VM : BaseMVIViewModel<VE, VS>,
        VB : ViewDataBinding,
        VE : BaseMVIEvent,
        VS : BaseMVIViewState> :
    BaseActivity<VB>(), BaseMVIContract<VB, VS> {
    abstract val viewModel: VM

    @CallSuper
    override fun onViewReady() {
        viewModel.states().observe(this, ::renderState)
    }
}