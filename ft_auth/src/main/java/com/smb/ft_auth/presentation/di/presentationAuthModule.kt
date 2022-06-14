package com.smb.ft_auth.presentation.di

import com.smb.ft_auth.presentation.login.LoginViewModel
import com.smb.ft_auth.presentation.signup.SignUpViewModel
import org.koin.dsl.module

val presentationLoginModule = module {

    factory<LoginViewModel> { LoginViewModel() }

    factory<SignUpViewModel> { SignUpViewModel(createAccountUseCase = get()) }
}