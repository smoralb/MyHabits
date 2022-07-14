package com.smb.core.domain

import com.google.firebase.auth.FirebaseAuth
import com.smb.core.data.Result

interface GetUserUidUseCase: UseCase<Unit, String>

class GetUserUidUseCaseImpl(
    private val getFirebaseAuth: FirebaseAuth
): GetUserUidUseCase {
    override suspend fun invoke(params: Unit): Result<String> =
        Result.Success(getFirebaseAuth.uid!!)
}