package com.example.core.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {
    (recyclerView.adapter as? ListAdapter<T, *>)?.submitList(items)
}