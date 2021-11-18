package com.example.core.presentation.adapters

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T: BaseItem, VH: BaseViewHolder<T>>(private val itemVariable: Int) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var items = listOf<T>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                notifyDataSetChanged()
            }
        }

    abstract fun updateData(newItems: List<T>)

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bind(items[position])

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