package com.smb.ft_main.domain.usecases

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.repository.HomeRepository

interface GetTasksUseCase : UseCase<Unit, HabitListModel>

class GetTasksUseCaseImpl(
    private val repository: HomeRepository
) : GetTasksUseCase {

    override suspend fun invoke(params: Unit): Result<HabitListModel> =
        repository.getTasks()
}
