package com.odroid.movieready.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(),
        BaseViewDataBindingContract<VB> {

    protected lateinit var viewBinder: VB

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = DataBindingUtil.setContentView(this, getMainLayout())
        viewBinder.lifecycleOwner = this
        onBindViewData(viewBinder)
        onViewReady()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        onViewDestroyed()
    }

    override fun onViewDestroyed() {
        viewBinder.unbind()
    }
}