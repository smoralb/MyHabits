package com.example.baseapplication.data.repository.mapper

import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.domain.model.SampleDataModel

interface SampleDataMapper {

    fun toDomainModel(entity: SampleApiResponseEntity?): List<SampleDataModel>
}