package com.odroid.movieready.base

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding

abstract class BaseMVIActivityWithEffect<
        VM : BaseMVIViewModelWithEffect<VE, VS, VF>,
        VB : ViewDataBinding,
        VE : BaseMVIEvent,
        VS : BaseMVIViewState,
        VF : BaseMVIViewEffect> :
    BaseMVIActivity<VM, VB, VE, VS>(), BaseMVIViewWithEffectContract<VB, VS, VF> {


    @CallSuper
    override fun onViewReady() {
        super.onViewReady()
        viewModel.effects().observe(this, ::renderEffect)
    }
}