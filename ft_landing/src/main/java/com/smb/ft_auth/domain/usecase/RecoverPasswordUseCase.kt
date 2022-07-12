package com.smb.ft_auth.domain.usecase

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.ft_auth.domain.repository.AuthRepository

interface RecoverPasswordUseCase: UseCase<RecoverPasswordUseCase.Params, Unit> {
    data class Params(val email: String)
}

class RecoverPasswordUseCaseImpl(
    val repository: AuthRepository
) : RecoverPasswordUseCase{
    override suspend fun invoke(params: RecoverPasswordUseCase.Params): Result<Unit> =
        repository.recoverPassword(params.email)
}