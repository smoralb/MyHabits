package com.smb.myhabits.domain.mocks

import com.smb.core.extensions.EMPTY_STRING
import com.smb.myhabits.domain.model.HabitListModel
import com.smb.myhabits.domain.model.HabitModel

private const val SAMPLE_ID = "SAMPLE ID"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_NAME = "SAMPLE NAME"

/**
 * MODEL
 */

//region Valid Models

internal val habitModelMock =
    HabitModel(
        id = SAMPLE_ID,
        name = SAMPLE_NAME,
        description = SAMPLE_DESCRIPTION
    )

internal val habitListModelMock = HabitListModel(
    habitList = listOf(habitModelMock)
)

//endregion

//region Empty models

internal val habitModelEmptyMock = HabitModel(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    description = EMPTY_STRING
)

internal val habitListEmptyChildModelMock = HabitListModel(
    habitList = listOf(habitModelMock)
)

internal val habitListEmptyModelMock = HabitListModel(
    habitList = emptyList()
)

//endregion