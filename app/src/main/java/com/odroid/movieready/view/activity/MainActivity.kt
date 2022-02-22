package com.odroid.movieready.view.activity

import com.odroid.movieready.R
import com.odroid.movieready.base.BaseActivity
import com.odroid.movieready.databinding.ActivityMainBinding
import com.odroid.movieready.view.fragment.MovieSuggestionFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var suggestionFragment: MovieSuggestionFragment

    override fun getMainLayout(): Int = R.layout.activity_main

    override fun onViewReady() {
        addSuggestionsFragment()
    }

    override fun onBindViewData(viewBinder: ActivityMainBinding) {

    }

    private fun addSuggestionsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        suggestionFragment = MovieSuggestionFragment()
        transaction.replace(R.id.suggestionHoldingFragment, suggestionFragment)
        transaction.addToBackStack(MovieSuggestionFragment::class.java.name)
        transaction.commitAllowingStateLoss()
    }
}