package com.smb.core.presentation.adapters

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
@SuppressLint("NotifyDataSetChanged")
abstract class BaseAdapter<T: BaseItem> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<T>()
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                notifyDataSetChanged()
            }
        }

    abstract fun updateData(newItems: List<T>)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder<BaseItem>).bind(items[position])

    override fun getItemCount() = items.size
}

open class BaseViewHolder<T>(
    private val itemVariableId: Int,
    private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }
}