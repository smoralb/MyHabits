package com.smb.ft_home.domain.usecases

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_home.domain.model.UpdateTaskModel
import com.smb.ft_home.domain.repository.HomeRepository

interface UpdateTaskUseCase : UseCase<UpdateTaskUseCase.Params, Unit> {
    data class Params(val task: UpdateTaskModel)
}

class UpdateTaskUseCaseImpl(
    private val repository: HomeRepository
) : UpdateTaskUseCase {

    override suspend fun invoke(params: UpdateTaskUseCase.Params): Result<Unit> =
        repository.updateTask(params.task)
}