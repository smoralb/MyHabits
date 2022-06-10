package com.smb.ft_auth.di

import com.smb.ft_auth.login.LoginViewModel
import com.smb.ft_auth.signup.SignUpViewModel
import org.koin.dsl.module

val presentationLoginModule = module {

    factory<LoginViewModel> { LoginViewModel() }

    factory<SignUpViewModel> { SignUpViewModel() }
}