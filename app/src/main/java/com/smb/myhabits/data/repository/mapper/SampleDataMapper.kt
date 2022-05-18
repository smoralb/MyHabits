package com.smb.myhabits.data.repository.mapper

import com.smb.myhabits.data.entity.SampleApiResponseEntity
import com.smb.myhabits.domain.model.SampleChildModel

interface SampleDataMapper {

    fun toDomainModel(entity: SampleApiResponseEntity?): SampleChildModel
}