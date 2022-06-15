package com.smb.ft_auth.presentation.di

import com.smb.ft_auth.presentation.login.LoginViewModel
import com.smb.ft_auth.presentation.login.mapper.LoginMapper
import com.smb.ft_auth.presentation.login.mapper.LoginMapperImpl
import com.smb.ft_auth.presentation.signup.SignUpViewModel
import com.smb.ft_auth.presentation.signup.mapper.SignUpMapper
import com.smb.ft_auth.presentation.signup.mapper.SignUpMapperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationAuthModule = module {

    single<LoginMapper> { LoginMapperImpl(context = androidContext()) }

    factory<LoginViewModel> { LoginViewModel(loginUseCase = get(), mapper = get()) }

    single<SignUpMapper> { SignUpMapperImpl(context = androidContext()) }

    factory<SignUpViewModel> {
        SignUpViewModel(
            createAccountUseCase = get(),
            mapper = get()
        )
    }
}