package com.odroid.movieready.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding

abstract class BaseMVIFragment<
        VM : BaseMVIViewModel<VE, VS>,
        VB : ViewDataBinding,
        VE : BaseMVIEvent,
        VS : BaseMVIViewState> : BaseFragmentV2<VB>(), BaseMVIContract<VB, VS> {

    abstract val viewModel: VM

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.states().observe(viewLifecycleOwner::getLifecycle, ::renderState)
    }

}