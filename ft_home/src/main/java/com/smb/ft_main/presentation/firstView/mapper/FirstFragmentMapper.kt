package com.smb.ft_main.presentation.firstView.mapper

import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_main.domain.model.HabitModel
import com.smb.ft_main.presentation.firstView.adapter.SampleDataItems

interface FirstFragmentMapper {
    fun mapItems(model: List<HabitModel>, itemClickListener: (String) -> Unit)
            : List<SampleDataItems.SampleDataItem>
}

class FirstFragmentMapperImpl : FirstFragmentMapper {

    override fun mapItems(model: List<HabitModel>, itemClickListener: (String) -> Unit) =
        model.map {
            SampleDataItems.SampleDataItem(
                isbn = EMPTY_STRING,
                title = it.name ?: "NAME",
                description = it.description ?: "DESCRIPTION",
                publisher = it.description ?: "DESCRIPTION",
                onItemClickListener = itemClickListener
            )
        }
}