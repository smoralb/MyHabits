package com.example.core.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

class ItemListDiffCallBack<I : Any> : DiffUtil.ItemCallback<I>() {

    override fun areItemsTheSame(oldItem: I, newItem: I): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: I, newItem: I): Boolean {
        TODO("Not yet implemented")
    }


}