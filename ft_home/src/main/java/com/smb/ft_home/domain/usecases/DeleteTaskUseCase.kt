package com.smb.ft_home.domain.usecases

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_home.domain.repository.HomeRepository
import com.smb.ft_home.domain.usecases.DeleteTaskUseCase.Params

interface DeleteTaskUseCase : UseCase<Params, Unit> {
    class Params(val document: String)
}

class DeleteTaskUseCaseImpl(
    private val repository: HomeRepository
) : DeleteTaskUseCase {

    override suspend fun invoke(params: Params): Result<Unit> =
        repository.deleteTask(params.document)
}