package com.smb.ft_main.domain.di

import com.smb.core.domain.LogOutUseCase
import com.smb.core.domain.LogOutUseCaseImpl
import com.smb.ft_main.domain.usecases.GetSampleDataUseCase
import com.smb.ft_main.domain.usecases.GetSampleDataUseCaseImpl
import org.koin.dsl.module

val baseDomainModule = module {
    factory<LogOutUseCase> { LogOutUseCaseImpl(firebaseAuth = get()) }
    factory<GetSampleDataUseCase> { GetSampleDataUseCaseImpl(get()) }
}