package com.smb.ft_auth.data.source

import com.smb.core.data.Result

interface AuthRemoteDataSource {
    suspend fun createNewAccount(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
}