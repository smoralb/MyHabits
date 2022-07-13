package com.smb.ft_main.presentation.mocks

import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.model.HabitModel

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

//endregion
