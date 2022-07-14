package com.smb.ft_home.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smb.core.presentation.adapters.BaseAdapter
import com.smb.core.presentation.adapters.BaseViewHolder
import com.smb.ft_home.BR
import com.smb.ft_home.R

class HomeFragmentAdapter : BaseAdapter<TaskDataItems.TaskDataItem>() {

    override fun updateData(newItems: List<TaskDataItems.TaskDataItem>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is TaskDataItems.TaskDataItem -> ITEM_TYPE
            else -> throw IllegalArgumentException()
        }

    private fun createItemViewHolder(parent: ViewGroup) =
        FirstFragmentItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_sample_data,
                parent,
                false
            )
        )

    inner class FirstFragmentItemViewHolder(binding: ViewDataBinding) :
        BaseViewHolder<TaskDataItems.TaskDataItem>(BR.item, binding)

    companion object {
        const val ITEM_TYPE = 0
    }
}