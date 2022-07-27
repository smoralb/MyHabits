package com.smb.ft_home.presentation.home.adapter

import com.smb.core.presentation.adapters.BaseItem

sealed class TaskDataItems : BaseItem {
    data class TaskDataItem(
        val id: String,
        val title: String,
        val description: String,
        val publisher: String,
        val onItemClickListener: (String) -> Unit
    ) : TaskDataItems() {
        override fun onItemClick() {
            onItemClickListener(id)
        }
    }
}