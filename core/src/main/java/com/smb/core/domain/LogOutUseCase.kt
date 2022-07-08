package com.smb.core.domain

import com.google.firebase.auth.FirebaseAuth
import com.smb.core.data.Result

interface LogOutUseCase: UseCase<Unit, Unit>

class LogOutUseCaseImpl(
    private val firebaseAuth: FirebaseAuth
): LogOutUseCase {

    override suspend fun invoke(params: Unit): Result<Unit> =
        Result.Success(firebaseAuth.signOut())
}