package com.example.habits.domain.di

import com.example.habits.domain.usecases.GetSampleDataUseCase
import com.example.habits.domain.usecases.GetSampleDataUseCaseImpl
import org.koin.dsl.module

val baseDomainModule = module {

    factory<GetSampleDataUseCase> { GetSampleDataUseCaseImpl(get()) }
}