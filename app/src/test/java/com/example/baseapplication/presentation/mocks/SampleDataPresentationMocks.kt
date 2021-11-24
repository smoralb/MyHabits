package com.example.baseapplication.presentation.mocks

import com.example.baseapplication.domain.model.SampleChildDetailsModel
import com.example.baseapplication.domain.model.SampleChildModel
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.core.extensions.EMPTY_STRING

const val SAMPLE_TITLE = "SAMPLE TITLE"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_PUBLISHER = "SAMPLE PUBLISHER"

/**
 * MODEL
 */

//region Valid Models

internal val sampleResponseChildDetailsModelMock =
    SampleChildDetailsModel(
        title = SAMPLE_TITLE,
        description = SAMPLE_DESCRIPTION,
        publisher = SAMPLE_PUBLISHER
    )

internal val sampleResponseChildModelMock = SampleChildModel(
    bookDetails = listOf(
        sampleResponseChildDetailsModelMock
    )
)

//endregion

/**
 * PARAMS
 */

internal val getSampleDataUseCaseParamsMock = GetSampleDataUseCase.Params(EMPTY_STRING)