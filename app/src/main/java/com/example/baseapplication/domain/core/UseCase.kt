package com.example.baseapplication.domain.core

import com.example.baseapplication.data.core.Result

interface UseCase<in P, out M> {

    suspend operator fun invoke(params: P): Result<M>
}