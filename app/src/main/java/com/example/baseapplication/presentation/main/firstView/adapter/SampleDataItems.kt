package com.example.baseapplication.presentation.main.firstView.adapter

import com.example.core.presentation.adapters.BaseItem

sealed class SampleDataItems: BaseItem() {
    data class SampleDataItem(
        val title: String,
        val description: String,
        val publisher: String
    ) : SampleDataItems()

    companion object {
        const val ITEM_TYPE = 0
    }
}