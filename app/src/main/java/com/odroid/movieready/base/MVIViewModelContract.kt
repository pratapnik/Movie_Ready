package com.odroid.movieready.base

interface MVIViewModelContract<Event : BaseMVIEvent> {
    fun processEvent(event: Event)
}