package com.smb.ft_home.data.repository

import com.smb.core.data.Result
import com.smb.ft_home.data.source.HomeRemoteSource
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.model.UpdateTaskModel
import com.smb.ft_home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val remoteSource: HomeRemoteSource
) : HomeRepository {

    override suspend fun getTasks(): Result<HabitListModel> = remoteSource.getTasks()

    override suspend fun createTask(task: CreateTaskModel): Result<Unit> =
        remoteSource.createTask(task)

    override suspend fun deleteTask(documentId: String): Result<Unit> =
        remoteSource.deleteTask(documentId)

    override suspend fun updateTask(task: UpdateTaskModel): Result<Unit> =
        remoteSource.updateTask(task)
}