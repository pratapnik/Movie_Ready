package com.odroid.movieready.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseMVIViewModel<
        VE : BaseMVIEvent,
        VS : BaseMVIViewState>
    : ViewModel(), MVIViewModelContract<VE> {

    private var _viewState: VS? = null

    private val _viewStates: MutableLiveData<VS> = MutableLiveData()

    protected var viewState: VS
        get() = _viewState
                ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            _viewState = value
            _viewStates.value = value
        }

    fun states(): LiveData<VS> = _viewStates

    internal val viewStateValue: VS
        get() {
            return viewState
        }
}