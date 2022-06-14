package com.smb.ft_auth.data.source

import com.google.firebase.auth.FirebaseAuth
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSourceImpl(
    private val auth: FirebaseAuth
) : AuthRemoteDataSource {
    override suspend fun createNewAccount(email: String, password: String): Result<Unit> =
        safeApiCall(
            apiCall = {
                auth.createUserWithEmailAndPassword(email, password)
            },
            mapper = {}
        )
}