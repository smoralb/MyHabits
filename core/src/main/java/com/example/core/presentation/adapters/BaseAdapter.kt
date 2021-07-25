package com.example.core.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    val itemVariable: Int,
    diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BaseViewHolder(itemVariable, binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bind(getItem(position))
}

class BaseViewHolder<T>(
    private val itemVariableId: Int,
    private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }
}