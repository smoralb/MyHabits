package com.example.baseapplication.presentation.main.firstView.mapper

import com.example.baseapplication.domain.model.SampleChildDetailsModel
import com.example.baseapplication.presentation.main.firstView.adapter.SampleDataItems

interface FirstFragmentMapper {
    fun mapItems(model: List<SampleChildDetailsModel>, itemClickListener: (String) -> Unit)
            : List<SampleDataItems.SampleDataItem>
}

class FirstFragmentMapperImpl : FirstFragmentMapper {

    override fun mapItems(model: List<SampleChildDetailsModel>, itemClickListener: (String) -> Unit) =
        model.map {
            SampleDataItems.SampleDataItem(
                isbn = it.isbn,
                title = it.title,
                description = it.description,
                publisher = it.publisher,
                onItemClickListener = itemClickListener
            )
        }
}