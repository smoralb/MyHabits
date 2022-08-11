package com.smb.core.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, newItems: List<BaseItem>) {
    (recyclerView.adapter as BaseAdapter<BaseItem>).updateData(newItems)
}