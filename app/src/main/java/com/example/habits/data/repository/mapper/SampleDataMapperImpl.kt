package com.example.habits.data.repository.mapper

import com.example.habits.data.entity.SampleApiChildDetailsEntity
import com.example.habits.data.entity.SampleApiResponseEntity
import com.example.habits.domain.model.SampleChildDetailsModel
import com.example.habits.domain.model.SampleChildModel

class SampleDataMapperImpl : SampleDataMapper {

    override fun toDomainModel(entity: SampleApiResponseEntity?): SampleChildModel =
        entity?.sampleChildResponseEntity?.first().let { section ->
            SampleChildModel(
                bookDetails = section?.bookDetails?.map { mapToChildrenDetails(it) }.orEmpty()
            )
        }


    private fun mapToChildrenDetails(entity: SampleApiChildDetailsEntity?): SampleChildDetailsModel =
        SampleChildDetailsModel(
            isbn = entity?.isbn.orEmpty(),
            title = entity?.title.orEmpty(),
            description = entity?.description.orEmpty(),
            publisher = entity?.publisher.orEmpty()
        )
}