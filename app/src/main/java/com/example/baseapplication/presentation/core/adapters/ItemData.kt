package com.example.baseapplication.presentation.core.adapters

import androidx.recyclerview.widget.DiffUtil

abstract class ItemData<T> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        TODO("Not yet implemented")
    }
}