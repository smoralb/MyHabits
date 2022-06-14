package com.smb.ft_auth.domain.repository

import com.smb.core.data.Result

interface AuthRepository {
    suspend fun createNewAccount(email: String, password: String): Result<Unit>
}