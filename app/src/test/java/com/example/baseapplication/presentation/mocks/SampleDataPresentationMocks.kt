package com.smb.myhabits.presentation.mocks

import com.smb.myhabits.domain.model.SampleChildDetailsModel
import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.myhabits.domain.usecases.GetSampleDataUseCase
import com.smb.core.extensions.EMPTY_STRING

const val SAMPLE_TITLE = "SAMPLE TITLE"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_PUBLISHER = "SAMPLE PUBLISHER"
private const val SAMPLE_ISBN = "SAMPLE ISBN"

/**
 * MODEL
 */

//region Valid Models

internal val sampleResponseChildDetailsModelMock =
    SampleChildDetailsModel(
        title = SAMPLE_TITLE,
        description = SAMPLE_DESCRIPTION,
        publisher = SAMPLE_PUBLISHER,
        isbn = SAMPLE_ISBN
    )

internal val sampleResponseChildModelMock = SampleChildModel(
    bookDetails = listOf(
        sampleResponseChildDetailsModelMock
    )
)

//endregion
