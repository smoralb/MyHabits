package com.smb.myhabits.com.smb.myhabits.domain.di

import com.smb.myhabits.com.smb.myhabits.domain.usecase.CheckSessionUseCase
import com.smb.myhabits.com.smb.myhabits.domain.usecase.CheckSessionUseCaseImpl
import com.smb.myhabits.com.smb.myhabits.domain.usecase.LogOutUseCase
import com.smb.myhabits.com.smb.myhabits.domain.usecase.LogOutUseCaseImpl
import org.koin.dsl.module

val domainAppModule = module {
    factory<CheckSessionUseCase> { CheckSessionUseCaseImpl(firebaseAuth = get()) }
    factory<LogOutUseCase> { LogOutUseCaseImpl(firebaseAuth = get()) }
}