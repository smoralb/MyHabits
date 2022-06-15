package com.smb.ft_auth.domain.usecase

import com.smb.core.data.Result
import com.smb.ft_auth.domain.repository.AuthRepository

class LoginUseCaseImpl(
    val repository: AuthRepository
): LoginUseCase {
    override suspend fun invoke(params: LoginUseCase.Params): Result<Unit> =
        repository.login(params.email, params.password)
}