package com.odroid.movieready.view.activity

import com.odroid.movieready.R
import com.odroid.movieready.base.BaseActivity
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.view.fragment.CategoriesFragment
import com.odroid.movieready.view.fragment.MovieSuggestionFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var categoriesFragment: CategoriesFragment

    lateinit var suggestionFragment: MovieSuggestionFragment

    override fun getMainLayout(): Int = R.layout.activity_main

    override fun onViewReady() {
        addCategoriesFragment()
        addSuggestionsFragment()
    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {

    }

    private fun addSuggestionsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        suggestionFragment = MovieSuggestionFragment()
        transaction.replace(R.id.suggestionHoldingFragment, suggestionFragment)
        transaction.commitAllowingStateLoss()
    }

    private fun addCategoriesFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        categoriesFragment = CategoriesFragment()
        transaction.replace(R.id.categoriesFragment, categoriesFragment)
        transaction.commitAllowingStateLoss()
    }
}