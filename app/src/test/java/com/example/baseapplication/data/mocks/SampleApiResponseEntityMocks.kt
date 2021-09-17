package com.example.baseapplication.data.mocks

import com.example.baseapplication.data.entity.SampleApiResponseChildEntity
import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.domain.model.SampleDataModel
import com.example.core.extensions.EMPTY_STRING

private const val SAMPLE_NAME = "SampleName"
private const val SAMPLE_IMAGE_URL = "imageURL"

/**
 * ENTITY
 */
internal val sampleResponseEntityValidMock = SampleApiResponseEntity(
    sampleChildResponseEntity = listOf(
        SampleApiResponseChildEntity(
            name = SAMPLE_NAME,
            image = SAMPLE_IMAGE_URL
        )
    )
)

internal val sampleResponseEntityNullMock = SampleApiResponseEntity(
    sampleChildResponseEntity = listOf(
        SampleApiResponseChildEntity(
            name = null,
            image = null
        )
    )
)

/**
 * MODEL
 */

internal val sampleResponseModelValidMock = listOf(
    SampleDataModel(
        name = SAMPLE_NAME,
        url = SAMPLE_IMAGE_URL
    )
)

internal val sampleResponseModelEmptyMock = listOf(
    SampleDataModel(
        name = EMPTY_STRING,
        url = EMPTY_STRING
    )
)