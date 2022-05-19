package com.smb.myhabits.domain.mocks


import com.smb.core.extensions.EMPTY_STRING

private const val SAMPLE_TITLE = "SAMPLE TITLE"
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

//region Empty models

internal val sampleResponseChildDetailsEmptyModelMock =
    SampleChildDetailsModel(
        title = EMPTY_STRING,
        description = EMPTY_STRING,
        publisher = EMPTY_STRING,
        isbn = EMPTY_STRING
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