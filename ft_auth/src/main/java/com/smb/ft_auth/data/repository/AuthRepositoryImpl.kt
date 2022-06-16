package com.smb.ft_auth.data.repository

import com.smb.core.data.Result
import com.smb.ft_auth.data.source.AuthRemoteDataSource
import com.smb.ft_auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val source: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun createNewAccount(email: String, password: String): Result<Unit> =
        source.createNewAccount(email, password)

    override suspend fun login(email: String, password: String): Result<Unit> =
        source.login(email, password)

    override suspend fun recoverPassword(email: String): Result<Unit> =
        source.recoverPassword(email = email)
}