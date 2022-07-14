package com.smb.ft_home.domain.di

import com.smb.core.domain.LogOutUseCase
import com.smb.core.domain.LogOutUseCaseImpl
import com.smb.ft_home.domain.usecases.CreateTaskUseCase
import com.smb.ft_home.domain.usecases.CreateTaskUseCaseImpl
import com.smb.ft_home.domain.usecases.GetTasksUseCase
import com.smb.ft_home.domain.usecases.GetTasksUseCaseImpl
import org.koin.dsl.module

val homeDomainModule = module {
    factory<LogOutUseCase> { LogOutUseCaseImpl(firebaseAuth = get()) }
    factory<GetTasksUseCase> { GetTasksUseCaseImpl(get()) }
    factory<CreateTaskUseCase> { CreateTaskUseCaseImpl(get()) }
}