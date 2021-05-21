package com.odroid.movieready.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.odroid.movieready.R

abstract class BaseFragmentV2<DataBindingView : ViewDataBinding>: Fragment() {

    protected lateinit var binding: DataBindingView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        val baseContainer = view.findViewById<FrameLayout>(R.id.frameContent)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            getLayout(),
            baseContainer,
            false
        )
        baseContainer.addView(binding.root)
        return view
    }

    @LayoutRes
    protected abstract fun getLayout(): Int
}