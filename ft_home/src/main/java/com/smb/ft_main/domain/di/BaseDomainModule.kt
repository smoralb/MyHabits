package com.smb.ft_main.domain.di

import com.smb.ft_main.domain.usecases.GetSampleDataUseCase
import com.smb.ft_main.domain.usecases.GetSampleDataUseCaseImpl
import org.koin.dsl.module

val baseDomainModule = module {

    factory<GetSampleDataUseCase> { GetSampleDataUseCaseImpl(get()) }
}