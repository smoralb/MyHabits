package com.example.baseapplication.presentation.main.firstView.mapper

import com.example.baseapplication.domain.model.SampleDataModel
import com.example.baseapplication.presentation.main.firstView.adapter.SampleDataItems

interface FirstFragmentMapper {
    fun mapItems(model: SampleDataModel): SampleDataItems.SampleDataItem
}

class FirstFragmentMapperImpl : FirstFragmentMapper {

    override fun mapItems(model: SampleDataModel) = SampleDataItems.SampleDataItem(
        name = model.name,
        url = model.url
    )

}