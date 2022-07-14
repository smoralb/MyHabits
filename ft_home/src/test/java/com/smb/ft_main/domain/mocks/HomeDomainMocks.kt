package com.smb.ft_main.domain.mocks

import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_main.domain.model.CreateTaskModel
import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.model.HabitModel

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

internal val taskModelMock = CreateTaskModel(
    name = "Test Name",
    description = "Test description"
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