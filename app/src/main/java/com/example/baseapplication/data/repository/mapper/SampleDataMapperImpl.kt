package com.example.baseapplication.data.repository.mapper

import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.domain.model.SampleDataModel

class SampleDataMapperImpl : SampleDataMapper {

    override fun toDomainModel(entity: SampleApiResponseEntity?): List<SampleDataModel> =
        entity?.sampleChildResponseEntity?.map {
            SampleDataModel(
                name = it.name.orEmpty(),
                url = it.image.orEmpty()
            )
        }.orEmpty()

}