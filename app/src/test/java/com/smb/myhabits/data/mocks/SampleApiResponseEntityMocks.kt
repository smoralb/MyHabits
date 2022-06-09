package com.smb.myhabits.data.mocks

import com.smb.core.extensions.EMPTY_STRING
import com.smb.myhabits.domain.model.HabitListModel
import com.smb.myhabits.domain.model.HabitModel

private const val SAMPLE_ID = "SAMPLE ID"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_NAME = "SAMPLE NAME"

/* MODEL */

//region Valid Models

internal val sampleHabitModelMock = HabitModel(
    id = SAMPLE_ID,
    name = SAMPLE_NAME,
    description = SAMPLE_DESCRIPTION
)

internal val sampleHabitListModelMock =
    HabitListModel(
        habitList = listOf(sampleHabitModelMock)
    )


//endregion

//region Empty models

internal val sampleHabitListModelEmptyMock =
    HabitListModel(
        habitList = emptyList()
    )

internal val sampleHabitModelEmptyMock = HabitModel(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    description = EMPTY_STRING
)

internal val sampleHabitListModelChildEmptyMock = HabitListModel(
    habitList = listOf(sampleHabitModelEmptyMock)
)

//endregion