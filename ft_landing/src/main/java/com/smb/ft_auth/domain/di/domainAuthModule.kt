package com.smb.ft_auth.domain.di

import com.smb.ft_auth.domain.usecase.CheckSessionUseCase
import com.smb.ft_auth.domain.usecase.CheckSessionUseCaseImpl
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCase
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCaseImpl
import com.smb.ft_auth.domain.usecase.LoginUseCase
import com.smb.ft_auth.domain.usecase.LoginUseCaseImpl
import com.smb.ft_auth.domain.usecase.RecoverPasswordUseCase
import com.smb.ft_auth.domain.usecase.RecoverPasswordUseCaseImpl
import org.koin.dsl.module

val domainAuthModule = module {
    factory<CreateNewAccountUseCase> { CreateNewAccountUseCaseImpl(repository = get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(repository = get()) }
    factory<RecoverPasswordUseCase> { RecoverPasswordUseCaseImpl(repository = get()) }
    factory<CheckSessionUseCase> { CheckSessionUseCaseImpl(firebaseAuth = get()) }
}