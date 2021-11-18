package com.example.baseapplication.data.repository.mapper

import com.example.baseapplication.data.entity.SampleApiChildDetailsEntity
import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.domain.model.SampleChildDetailsModel
import com.example.baseapplication.domain.model.SampleChildModel

class SampleDataMapperImpl : SampleDataMapper {

    override fun toDomainModel(entity: SampleApiResponseEntity?): SampleChildModel =
        entity?.sampleChildResponseEntity?.first().let { section ->
            SampleChildModel(
                bookDetails = section?.bookDetails?.map { mapToChildrenDetails(it) }.orEmpty()
            )
        }


    private fun mapToChildrenDetails(entity: SampleApiChildDetailsEntity?): SampleChildDetailsModel =
        SampleChildDetailsModel(
            title = entity?.title.orEmpty(),
            description = entity?.description.orEmpty(),
            publisher = entity?.publisher.orEmpty()
        )

}