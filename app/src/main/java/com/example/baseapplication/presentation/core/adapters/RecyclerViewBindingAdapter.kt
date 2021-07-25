package com.example.baseapplication.presentation.core.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {
    (recyclerView.adapter as? ListAdapter<T, *>)?.submitList(items)
}