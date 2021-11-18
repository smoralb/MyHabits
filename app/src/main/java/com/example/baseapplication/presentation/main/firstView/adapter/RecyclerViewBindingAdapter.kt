package com.example.baseapplication.presentation.main.firstView.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, newItems: List<SampleDataItems.SampleDataItem>) {
    if (newItems.isNotEmpty()) {
        (recyclerView.adapter as FirstFragmentAdapter).updateItems(newItems)
    }
}