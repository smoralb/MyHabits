package com.smb.ft_auth.domain.usecase

import com.smb.core.data.Result
import com.smb.core.extensions.isAValidEmail
import com.smb.ft_auth.R
import com.smb.ft_auth.domain.repository.AuthRepository

class LoginUseCaseImpl(
    val repository: AuthRepository
) : LoginUseCase {
    override suspend fun invoke(params: LoginUseCase.Params): Result<Unit> =
        if (params.email.isAValidEmail()) {
            if (params.password.isNotBlank()) {
                repository.login(params.email, params.password)
            } else Result.Error(errorId = R.string.auth_incorrect_password)
        } else Result.Error(errorId = R.string.auth_incorrect_mail)

}