package com.smb.myhabits.com.smb.myhabits.domain.di

import com.smb.myhabits.com.smb.myhabits.domain.usecase.CheckSessionUseCase
import com.smb.myhabits.com.smb.myhabits.domain.usecase.CheckSessionUseCaseImpl
import org.koin.dsl.module

val domainAppModule = module {
    factory<CheckSessionUseCase> { CheckSessionUseCaseImpl(firebaseAuth = get()) }
}