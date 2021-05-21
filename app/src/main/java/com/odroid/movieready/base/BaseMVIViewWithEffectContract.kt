package com.odroid.movieready.base

import androidx.databinding.ViewDataBinding

interface BaseMVIViewWithEffectContract<
        VB : ViewDataBinding,
        VS : BaseMVIViewState,
        VE : BaseMVIViewEffect> : BaseMVIContract<VB, VS> {
    fun renderEffect(effect: VE)
}