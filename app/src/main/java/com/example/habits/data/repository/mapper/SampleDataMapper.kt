package com.example.habits.data.repository.mapper

import com.example.habits.data.entity.SampleApiResponseEntity
import com.example.habits.domain.model.SampleChildModel

interface SampleDataMapper {

    fun toDomainModel(entity: SampleApiResponseEntity?): SampleChildModel
}