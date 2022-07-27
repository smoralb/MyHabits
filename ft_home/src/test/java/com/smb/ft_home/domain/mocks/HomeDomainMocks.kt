package com.smb.ft_home.domain.mocks

import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.HabitModel

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
