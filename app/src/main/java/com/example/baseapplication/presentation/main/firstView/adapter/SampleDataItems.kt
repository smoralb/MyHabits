package com.example.baseapplication.presentation.main.firstView.adapter

import com.example.core.presentation.adapters.BaseItem

sealed class SampleDataItems: BaseItem {
    data class SampleDataItem(
        val title: String,
        val description: String,
        val publisher: String,
        val onItemClickListener: () -> Unit
    ) : SampleDataItems() {
        override fun onItemClick(itemId: Int?) {
            onItemClickListener.invoke()
        }
    }
}