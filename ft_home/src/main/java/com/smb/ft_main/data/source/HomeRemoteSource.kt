package com.smb.ft_main.data.source

import com.smb.core.data.Result
import com.smb.ft_main.domain.model.CreateTaskModel
import com.smb.ft_main.domain.model.HabitListModel

interface HomeRemoteSource {

    suspend fun getTasks(): Result<HabitListModel>
    suspend fun createTask(task: CreateTaskModel): Result<Unit>
}