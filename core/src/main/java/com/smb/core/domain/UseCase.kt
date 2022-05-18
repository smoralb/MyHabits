package com.smb.core.domain

import com.smb.core.data.Result

interface UseCase<in P, out M> {

    suspend operator fun invoke(params: P): Result<M>
}