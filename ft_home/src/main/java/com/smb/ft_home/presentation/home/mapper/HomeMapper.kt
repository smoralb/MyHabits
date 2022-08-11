package com.smb.ft_home.presentation.home.mapper

import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_home.domain.model.HabitModel
import com.smb.ft_home.presentation.home.adapter.TaskDataItems

interface FirstFragmentMapper {
    fun mapItems(model: List<HabitModel>, itemClickListener: (String) -> Unit)
            : List<TaskDataItems.TaskDataItem>
}

class HomeMapperImpl : FirstFragmentMapper {

    override fun mapItems(model: List<HabitModel>, itemClickListener: (String) -> Unit): List<TaskDataItems.TaskDataItem> {
        return if (model.isNotEmpty()) {
            model.map {
                TaskDataItems.TaskDataItem(
                    id = it.id ?: EMPTY_STRING,
                    title = it.name ?: "NAME",
                    description = it.description ?: "DESCRIPTION",
                    publisher = it.description ?: "DESCRIPTION",
                    onItemClickListener = itemClickListener
                )
            }
        } else emptyList()
    }
}