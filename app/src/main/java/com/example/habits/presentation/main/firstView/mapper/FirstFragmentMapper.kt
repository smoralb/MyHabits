package com.example.habits.presentation.main.firstView.mapper

import com.example.habits.domain.model.SampleChildDetailsModel
import com.example.habits.presentation.main.firstView.adapter.SampleDataItems

interface FirstFragmentMapper {
    fun mapItems(model: List<SampleChildDetailsModel>, itemClickListener: () -> Unit)
            : List<SampleDataItems.SampleDataItem>
}

class FirstFragmentMapperImpl : FirstFragmentMapper {

    override fun mapItems(model: List<SampleChildDetailsModel>, itemClickListener: () -> Unit) =
        model.map {
            SampleDataItems.SampleDataItem(
                title = it.title,
                description = it.description,
                publisher = it.publisher,
                onItemClickListener = itemClickListener
            )
        }
}