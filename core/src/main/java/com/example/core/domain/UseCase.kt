package com.example.core.domain

interface UseCase<in P, out M> {

    suspend operator fun invoke(params: P): com.example.core.data.Result<M>
}