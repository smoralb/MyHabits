package com.smb.myhabits.data.mocks

import com.smb.myhabits.data.entity.SampleApiChildDetailsEntity
import com.smb.myhabits.data.entity.SampleApiResponseChildEntity
import com.smb.myhabits.data.entity.SampleApiResponseEntity
import com.smb.myhabits.domain.model.SampleChildDetailsModel
import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.core.extensions.EMPTY_STRING

private const val SAMPLE_TITLE = "SAMPLE TITLE"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_PUBLISHER = "SAMPLE PUBLISHER"
private const val SAMPLE_ISBN = "SAMPLE ISBN"

/* ENTITY */

//region Valid Entities

internal val sampleApiResponseChildDetailsEntityMock = SampleApiChildDetailsEntity(
    title = SAMPLE_TITLE,
    description = SAMPLE_DESCRIPTION,
    publisher = SAMPLE_PUBLISHER,
    isbn = SAMPLE_ISBN
)

internal val sampleApiResponseValidEntityMock = SampleApiResponseEntity(
    sampleChildResponseEntity = listOf(
        SampleApiResponseChildEntity(
            bookDetails = listOf(
                sampleApiResponseChildDetailsEntityMock
            )
        )
    )
)

//endregion

//region Null Entities

internal val sampleApiResponseChildDetailsNullEntityMock = SampleApiChildDetailsEntity(
    title = null,
    description = null,
    publisher = null,
    isbn = null
)

internal val sampleApiResponseChildDetailsListNullEntityMock = SampleApiResponseEntity(
    sampleChildResponseEntity = listOf(
        SampleApiResponseChildEntity(
            bookDetails = listOf(
                sampleApiResponseChildDetailsNullEntityMock
            )
        )
    )
)

internal val sampleApiResponseNullEntityMock = SampleApiResponseEntity(
    sampleChildResponseEntity = listOf(
        SampleApiResponseChildEntity(
            bookDetails = null
        )
    )
)

//endregion

/* MODEL */

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