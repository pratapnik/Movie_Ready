package com.odroid.movieready.base

abstract class BaseMVIViewModelWithEffect<
        VE : BaseMVIEvent,
        VS : BaseMVIViewState,
        VF : BaseMVIViewEffect> : BaseMVIViewModel<VE, VS>() {
    
    private var _viewEffect: VF? = null
    private val _viewEffects: SingleLiveData<VF> = SingleLiveData()

    protected var viewEffect: VF
        get() = _viewEffect
                ?: throw UninitializedPropertyAccessException("\"viewEffect\" was queried before being initialized")
        set(value) {
            _viewEffect = value
            _viewEffects.value = value
        }

    fun effects(): SingleLiveData<VF> = _viewEffects

    internal val viewEffectValue: VF
        get() {
            return viewEffect
        }
}