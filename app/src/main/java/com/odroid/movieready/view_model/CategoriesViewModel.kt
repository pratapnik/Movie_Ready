package com.odroid.movieready.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.odroid.movieready.base.BaseMVIViewModelWithEffect
import com.odroid.movieready.data.GameSuggestionRepositoryImpl
import com.odroid.movieready.entity.Category
import com.odroid.movieready.view_intent.CategoriesViewIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel : BaseMVIViewModelWithEffect<
        CategoriesViewIntent.ViewEvent,
        CategoriesViewIntent.ViewState,
        CategoriesViewIntent.ViewEffect>() {

    private val firebaseRealtimeDb =
        Firebase.database("https://movie-ready-default-rtdb.firebaseio.com/").reference

    override fun processEvent(event: CategoriesViewIntent.ViewEvent) {
        when (event) {
            CategoriesViewIntent.ViewEvent.LoadCategories -> {
                getCategoryList()
            }
        }
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            fetchGameCategories()
        }
    }

    private fun fetchGameCategories() {
        val tempCatList = arrayListOf<Category>()
        firebaseRealtimeDb.child("categories").get().addOnSuccessListener { dataSnapshot ->
            dataSnapshot.children.forEach {
                val map = it.value as HashMap<String, Any>
                val categoryUrl = map["url"] as String
                val category = Category(
                    map["categoryId"] as Long,
                    map["categoryTitle"] as String,
                    map["priority"] as Long,
                    categoryUrl
                )
                tempCatList.add(category)
            }
            viewEffect = CategoriesViewIntent.ViewEffect.UpdateUiWithCategories(tempCatList)
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }
}