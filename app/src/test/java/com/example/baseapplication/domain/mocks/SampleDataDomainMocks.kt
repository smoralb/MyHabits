package com.example.baseapplication.domain.mocks

import com.example.baseapplication.domain.model.SampleChildDetailsModel
import com.example.baseapplication.domain.model.SampleChildModel
import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.core.extensions.EMPTY_STRING

private const val SAMPLE_TITLE = "SAMPLE TITLE"
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

//region Empty models

internal val sampleResponseChildDetailsEmptyModelMock =
    SampleChildDetailsModel(
        title = EMPTY_STRING,
        description = EMPTY_STRING,
        publisher = EMPTY_STRING
    )

internal val sampleResponseModelEmptyMock = SampleChildModel(
    bookDetails = listOf(
        sampleResponseChildDetailsEmptyModelMock
    )
)

internal val sampleResponseModelEmptyListMock = SampleChildModel(
    bookDetails = emptyList()
)

//endregion

/**
 * PARAMS
 */

internal val getSampleDataUseCaseParamsMock = GetSampleDataUseCase.Params(EMPTY_STRING)