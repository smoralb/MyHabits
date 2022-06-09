package com.smb.ft_auth.di

import com.smb.ft_auth.login.LoginViewModel
import org.koin.dsl.module

val presentationLoginModule = module {

    factory<LoginViewModel> { LoginViewModel() }
}