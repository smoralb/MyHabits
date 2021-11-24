package com.example.baseapplication.domain.di

import com.example.baseapplication.domain.usecases.GetSampleDataUseCase
import com.example.baseapplication.domain.usecases.GetSampleDataUseCaseImpl
import org.koin.dsl.module

val baseDomainModule = module {

    factory<GetSampleDataUseCase> { GetSampleDataUseCaseImpl(get()) }
}