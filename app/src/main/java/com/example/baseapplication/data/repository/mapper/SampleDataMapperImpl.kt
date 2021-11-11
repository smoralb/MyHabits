package com.example.baseapplication.data.repository.mapper

import com.example.baseapplication.data.entity.SampleApiChildDetailsEntity
import com.example.baseapplication.data.entity.SampleApiResponseChildEntity
import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.domain.model.SampleChildDetailsModel
import com.example.baseapplication.domain.model.SampleChildModel
import com.example.baseapplication.domain.model.SampleDataModel

class SampleDataMapperImpl : SampleDataMapper {

    override fun toDomainModel(entity: SampleApiResponseEntity?): SampleDataModel =
        SampleDataModel(
            results = entity?.sampleChildResponseEntity?.map { mapToChildItem(it) }.orEmpty()
        )

    private fun mapToChildItem(entity: SampleApiResponseChildEntity?): SampleChildModel =
        SampleChildModel(
            bookDetails = entity?.bookDetails?.map { mapToChildrenDetails(it) }.orEmpty()
        )


    private fun mapToChildrenDetails(entity: SampleApiChildDetailsEntity?): SampleChildDetailsModel =
        SampleChildDetailsModel(
            title = entity?.title.orEmpty(),
            description = entity?.description.orEmpty(),
            publisher = entity?.publisher.orEmpty()
        )

}