package com.smb.ft_home.data.source

import com.smb.core.data.Result
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.model.HabitListModel

interface HomeRemoteSource {

    suspend fun getTasks(): Result<HabitListModel>
    suspend fun createTask(task: CreateTaskModel): Result<Unit>
    suspend fun deleteTask(documentId: String): Result<Unit>
}