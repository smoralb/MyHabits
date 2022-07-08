package com.smb.ft_main.presentation.firstView.adapter

import com.smb.core.presentation.adapters.BaseItem

sealed class SampleDataItems : BaseItem {
    data class SampleDataItem(
        val isbn: String,
        val title: String,
        val description: String,
        val publisher: String,
        val onItemClickListener: (String) -> Unit
    ) : SampleDataItems() {
        override fun onItemClick() {
            onItemClickListener(isbn)
        }
    }
}