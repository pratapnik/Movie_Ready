package com.odroid.movieready.view_intent

import com.odroid.movieready.base.BaseMVIEvent
import com.odroid.movieready.base.BaseMVIViewEffect
import com.odroid.movieready.base.BaseMVIViewState
import com.odroid.movieready.entity.Category

class CategoriesViewIntent {
    sealed class ViewEvent : BaseMVIEvent {
        object LoadCategories: ViewEvent()
    }

    sealed class ViewState : BaseMVIViewState {

    }

    sealed class ViewEffect : BaseMVIViewEffect {
        class UpdateUiWithCategories(val categoryList: ArrayList<Category>): ViewEffect()
    }
}