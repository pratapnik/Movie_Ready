package com.odroid.movieready.view.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.odroid.movieready.R
import com.odroid.movieready.base.BaseMVIFragmentWithEffect
import com.odroid.movieready.databinding.CategoriesFragmentBinding
import com.odroid.movieready.entity.Category
import com.odroid.movieready.view.adapter.CategoriesAdapter
import com.odroid.movieready.view_intent.CategoriesViewIntent
import com.odroid.movieready.view_model.CategoriesViewModel

class CategoriesFragment : BaseMVIFragmentWithEffect<
        CategoriesViewModel,
        CategoriesFragmentBinding,
        CategoriesViewIntent.ViewEvent,
        CategoriesViewIntent.ViewState,
        CategoriesViewIntent.ViewEffect>(), CategoriesAdapter.CategoryItemListener {

    private val categoriesViewModel: CategoriesViewModel by viewModels()

    private lateinit var categoriesAdapter: CategoriesAdapter

    override val viewModel: CategoriesViewModel
        get() = categoriesViewModel

    override fun getLayout(): Int = R.layout.categories_fragment

    override fun initializeViews() {
        viewModel.processEvent(CategoriesViewIntent.ViewEvent.LoadCategories)
    }

    private fun initCategoriesAdapter(categoryList: ArrayList<Category>) {
        val categoryLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        categoriesAdapter = CategoriesAdapter(
            requireContext(),
            categoryList,
            this
        )

        binding.rvCategories.apply {
            layoutManager = categoryLayoutManager
            adapter = categoriesAdapter
        }
    }

    override fun renderState(state: CategoriesViewIntent.ViewState) {
        TODO("Not yet implemented")
    }

    override fun renderEffect(effect: CategoriesViewIntent.ViewEffect) {
        when (effect) {
            is CategoriesViewIntent.ViewEffect.UpdateUiWithCategories -> {
                initCategoriesAdapter(effect.categoryList)
            }
        }
    }

    override fun onCategoryClick(category: Category) {
        Toast.makeText(requireContext(), "Category ${category.categoryTitle} clicked", Toast.LENGTH_LONG).show()
    }
}