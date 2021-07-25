package com.example.baseapplication.presentation.main.firstView.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.baseapplication.R
import com.example.baseapplication.presentation.core.adapters.BaseAdapter

class FirstFragmentAdapter : BaseAdapter<SampleDataItems>(DiffCallBack) {

    object DiffCallBack : DiffUtil.ItemCallback<SampleDataItems>() {
        override fun areItemsTheSame(oldItem: SampleDataItems, newItem: SampleDataItems): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SampleDataItems, newItem: SampleDataItems) =
            oldItem == newItem
    }

    override fun getItemViewType(position: Int) =
        when (getItem(position)) {
            is SampleDataItems.SampleDataItem -> R.layout.item_sample_data
        }
}