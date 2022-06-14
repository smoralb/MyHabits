package com.smb.ft_auth.domain.usecase

import com.smb.core.domain.UseCase

interface CreateNewAccountUseCase : UseCase<CreateNewAccountUseCase.Params, Unit> {
    data class Params(val email: String, val password: String)
}