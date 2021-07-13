package com.example.baseapplication.data.repository.mapper

import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.domain.model.SampleDataModel

class SampleDataMapperImpl: SampleDataMapper {

    override fun toDomainModel(entity: SampleApiResponseEntity?): SampleDataModel {
            return SampleDataModel(
                name = entity?.sampleChildResponseEntity?.first()?.name.orEmpty(),
                url = entity?.sampleChildResponseEntity?.first()?.image.orEmpty()
            )
    }
}