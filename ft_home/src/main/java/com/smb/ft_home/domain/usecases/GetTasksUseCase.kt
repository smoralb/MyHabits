package com.smb.ft_home.domain.usecases

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_home.domain.model.HabitListModel
import com.smb.ft_home.domain.repository.HomeRepository

interface GetTasksUseCase : UseCase<Unit, HabitListModel>

class GetTasksUseCaseImpl(
    private val repository: HomeRepository
) : GetTasksUseCase {

    override suspend fun invoke(params: Unit): Result<HabitListModel> =
        repository.getTasks()
}
