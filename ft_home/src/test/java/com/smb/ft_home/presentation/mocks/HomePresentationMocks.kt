package com.smb.ft_home.presentation.mocks

import com.smb.core.extensions.EMPTY_STRING
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.HabitModel
import com.smb.ft_home.domain.model.UpdateTaskModel

const val SAMPLE_ID = "SAMPLE ID"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_NAME = "SAMPLE NAME"

/**
 * MODEL
 */

//region Valid Models

internal val presentationHabitModelMock = HabitModel(
    id = SAMPLE_ID,
    name = SAMPLE_NAME,
    description = SAMPLE_DESCRIPTION
)

internal val presentationHabitListModelMock = HabitListModel(
    habitList = listOf(presentationHabitModelMock)
)

internal val updateTaskModelMock = UpdateTaskModel(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    description = EMPTY_STRING
)

//endregion
