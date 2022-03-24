package com.odroid.movieready.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.odroid.movieready.R
import com.odroid.movieready.databinding.CategoryItemBinding
import com.odroid.movieready.entity.Category

class CategoriesAdapter(
    private val context: Context,
    var categoryList: ArrayList<Category>,
    private val categoryItemListener: CategoryItemListener
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(position: Int) {
            binding.tvCategoryTitle.text = categoryList[position].categoryTitle
            binding.root.setOnClickListener {
                categoryItemListener.onCategoryClick(categoryList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoriesViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = categoryList.size

    interface CategoryItemListener {
        fun onCategoryClick(category: Category)
    }

}