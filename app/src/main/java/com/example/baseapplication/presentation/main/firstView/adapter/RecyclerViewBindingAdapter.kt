package com.example.baseapplication.presentation.main.firstView.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.presentation.adapters.BaseAdapter
import com.example.core.presentation.adapters.BaseItem

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, newItems: List<SampleDataItems.SampleDataItem>) {
    if (newItems.isNotEmpty()) {
        (recyclerView.adapter as BaseAdapter<BaseItem>).updateData(newItems)
    }
}