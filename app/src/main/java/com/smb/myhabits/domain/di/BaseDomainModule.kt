package com.smb.myhabits.domain.di

import com.smb.myhabits.domain.usecases.GetSampleDataUseCase
import com.smb.myhabits.domain.usecases.GetSampleDataUseCaseImpl
import org.koin.dsl.module

val baseDomainModule = module {

    factory<GetSampleDataUseCase> { GetSampleDataUseCaseImpl(get()) }
}