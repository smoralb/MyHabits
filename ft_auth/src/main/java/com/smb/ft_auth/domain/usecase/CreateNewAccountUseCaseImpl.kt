package com.smb.ft_auth.domain.usecase

import com.smb.core.data.Result
import com.smb.core.extensions.isAValidEmail
import com.smb.core.extensions.isAValidPassword
import com.smb.ft_auth.R
import com.smb.ft_auth.domain.repository.AuthRepository

class CreateNewAccountUseCaseImpl(
    val repository: AuthRepository
) : CreateNewAccountUseCase {
    override suspend fun invoke(params: CreateNewAccountUseCase.Params): Result<Unit> =
        if (params.email.isAValidEmail()) {
            if (params.password.isAValidPassword()) {
                repository.createNewAccount(params.email, params.password)
            } else Result.Error(errorId = R.string.sign_up_incorrect_password)
        } else Result.Error(errorId = R.string.sign_up_incorrect_mail)
}