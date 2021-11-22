package com.example.baseapplication.presentation.main.firstView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapplication.BR
import com.example.baseapplication.R
import com.example.core.presentation.adapters.BaseAdapter
import com.example.core.presentation.adapters.BaseViewHolder

class FirstFragmentAdapter : BaseAdapter<SampleDataItems.SampleDataItem>() {

    override fun updateData(newItems: List<SampleDataItems.SampleDataItem>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is SampleDataItems.SampleDataItem -> ITEM_TYPE
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
        BaseViewHolder<SampleDataItems.SampleDataItem>(BR.item, binding)

    companion object {
        const val ITEM_TYPE = 0
    }
}