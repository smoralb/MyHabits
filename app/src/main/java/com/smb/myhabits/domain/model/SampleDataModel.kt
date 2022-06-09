package com.smb.myhabits.domain.model

import com.smb.core.extensions.EMPTY_STRING


data class HabitListModel(
    val habitList: List<HabitModel>
)

data class HabitModel(
    val id: String? = EMPTY_STRING,
    val description: String? = EMPTY_STRING,
    val name: String? = EMPTY_STRING
)