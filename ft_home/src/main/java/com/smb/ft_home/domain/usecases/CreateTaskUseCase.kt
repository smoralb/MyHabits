package com.smb.ft_home.domain.usecases

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_home.domain.model.CreateTaskModel
import com.smb.ft_home.domain.repository.HomeRepository
import com.smb.ft_home.domain.usecases.CreateTaskUseCase.Params

interface CreateTaskUseCase : UseCase<Params, Unit> {
    data class Params(val task: CreateTaskModel)
}

data class CreateTaskUseCaseImpl(
    private var repository: HomeRepository
) : CreateTaskUseCase {

    override suspend fun invoke(params: Params): Result<Unit> =
        repository.createTask(params.task)
}