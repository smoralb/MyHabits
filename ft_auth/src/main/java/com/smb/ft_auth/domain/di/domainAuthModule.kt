package com.smb.ft_auth.domain.di

import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCase
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCaseImpl
import com.smb.ft_auth.domain.usecase.LoginUseCase
import com.smb.ft_auth.domain.usecase.LoginUseCaseImpl
import org.koin.dsl.module

val domainAuthModule = module {
    factory<CreateNewAccountUseCase> { CreateNewAccountUseCaseImpl(repository = get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(repository = get()) }
}