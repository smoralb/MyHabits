package com.smb.myhabits.data.repository.mapper

import com.smb.myhabits.data.entity.SampleApiChildDetailsEntity
import com.smb.myhabits.data.entity.SampleApiResponseEntity
import com.smb.myhabits.domain.model.SampleChildDetailsModel
import com.smb.myhabits.domain.model.SampleChildModel

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