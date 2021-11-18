package com.example.baseapplication.presentation.main.firstView.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.baseapplication.BR
import com.example.baseapplication.R
import com.example.baseapplication.presentation.main.firstView.adapter.SampleDataItems.Companion.ITEM_TYPE
import com.example.core.presentation.adapters.BaseItem

class FirstFragmentAdapter : RecyclerView.Adapter<FirstFragmentAdapter.FirstFragmentViewHolder>() {

    private var itemViewModels: List<BaseItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstFragmentViewHolder =
        when (viewType) {
            ITEM_TYPE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        when (itemViewModels[position]) {
            is SampleDataItems.SampleDataItem -> ITEM_TYPE
            else -> throw IllegalArgumentException()
        }

    override fun getItemCount(): Int = itemViewModels.size

    override fun onBindViewHolder(holder: FirstFragmentViewHolder, position: Int) {
        holder.bind(itemViewModels[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<SampleDataItems.SampleDataItem>) {
        itemViewModels = items
        notifyDataSetChanged()
    }

    private fun createItemViewHolder(parent: ViewGroup) =
        FirstFragmentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_sample_data,
                parent,
                false
            )
        )

    class FirstFragmentViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: BaseItem) {
            binding.setVariable(BR.item, itemViewModel)
            binding.executePendingBindings()
        }
    }
}