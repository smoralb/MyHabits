package com.smb.ft_auth.domain.usecase

import com.smb.core.data.Result
import com.smb.core.domain.UseCase
import com.smb.core.extensions.isAValidEmail
import com.smb.core.extensions.isAValidPassword
import com.smb.ft_auth.R
import com.smb.ft_auth.domain.repository.AuthRepository

interface CreateNewAccountUseCase : UseCase<CreateNewAccountUseCase.Params, Unit> {
    data class Params(val email: String, val password: String)
}

class CreateNewAccountUseCaseImpl(
    val repository: AuthRepository
) : CreateNewAccountUseCase {
    override suspend fun invoke(params: CreateNewAccountUseCase.Params): Result<Unit> =
        if (params.email.isAValidEmail()) {
            if (params.password.isAValidPassword()) {
                repository.createNewAccount(params.email, params.password)
            } else Result.Error(errorId = R.string.auth_incorrect_password)
        } else Result.Error(errorId = R.string.auth_incorrect_mail)
}