package com.smb.myhabits.com.smb.myhabits.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.smb.core.data.Result
import com.smb.core.domain.UseCase

interface CheckSessionUseCase : UseCase<Unit, Boolean>

class CheckSessionUseCaseImpl(
    private val firebaseAuth: FirebaseAuth
) : CheckSessionUseCase {
    override suspend fun invoke(params: Unit): Result<Boolean> =
        Result.Success(firebaseAuth.currentUser != null)
}