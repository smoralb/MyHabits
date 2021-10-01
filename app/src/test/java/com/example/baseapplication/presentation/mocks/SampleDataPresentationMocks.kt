package com.example.baseapplication.presentation.mocks

import com.example.baseapplication.domain.model.SampleDataModel
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.core.extensions.EMPTY_STRING

const val SAMPLE_NAME = "SampleName"
private const val SAMPLE_IMAGE_URL = "imageURL"

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

/**
 * PARAMS
 */

internal val getSampleDataUseCaseParamsMock = GetSampleDataUseCase.Params(EMPTY_STRING)