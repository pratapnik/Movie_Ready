package com.odroid.movieready.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding

abstract class BaseMVIFragmentWithEffect<
        VM : BaseMVIViewModelWithEffect<VE, VS, VF>,
        VB : ViewDataBinding,
        VE : BaseMVIEvent,
        VS : BaseMVIViewState,
        VF : BaseMVIViewEffect> : BaseMVIFragment<VM, VB, VE, VS>(),
    BaseMVIViewWithEffectContract<VB, VS, VF> {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.effects().observe(viewLifecycleOwner::getLifecycle, ::renderEffect)
    }
}