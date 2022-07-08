package com.smb.myhabits.com.smb.myhabits.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.smb.core.data.Result
import com.smb.core.domain.UseCase

interface LogOutUseCase: UseCase<Unit, Unit>

class LogOutUseCaseImpl(
    private val firebaseAuth: FirebaseAuth
): LogOutUseCase {

    override suspend fun invoke(params: Unit): Result<Unit> =
        Result.Success(firebaseAuth.signOut())
}