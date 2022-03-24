package com.odroid.movieready.view_model

import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.entity.Category
import com.odroid.movieready.view_intent.CategoriesViewIntent

class CategoriesViewModel : BaseMVIViewModelWithEffect<
        CategoriesViewIntent.ViewEvent,
        CategoriesViewIntent.ViewState,
        CategoriesViewIntent.ViewEffect>() {

    override fun processEvent(event: CategoriesViewIntent.ViewEvent) {
        when (event) {
            CategoriesViewIntent.ViewEvent.LoadCategories -> {
                val categoryList = getCategoryList()
                viewEffect = CategoriesViewIntent.ViewEffect.UpdateUiWithCategories(categoryList)
            }
        }
    }

    private fun getCategoryList(): ArrayList<Category> {
        val categoryList = arrayListOf<Category>()
        categoryList.add(Category("Bollywood Movies"))
        categoryList.add(Category("Animals"))
        categoryList.add(Category("Tv Shows"))
        categoryList.add(Category("Sports"))
        categoryList.add(Category("Countries"))
        return categoryList
    }
}