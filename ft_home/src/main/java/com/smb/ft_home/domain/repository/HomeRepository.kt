package com.smb.ft_home.domain.repository

import com.smb.core.data.Result
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.UpdateTaskModel

interface HomeRepository {

    suspend fun getTasks(): Result<HabitListModel>
    suspend fun createTask(task: CreateTaskModel): Result<Unit>
    suspend fun deleteTask(documentId: String): Result<Unit>
    suspend fun updateTask(task: UpdateTaskModel): Result<Unit>
}