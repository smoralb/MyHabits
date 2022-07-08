package com.smb.navigator.di.navigation.di

import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.navigator.di.navigation.AuthNavigatorImpl
import org.koin.dsl.module

val navigatorModule = module {
    single<AuthNavigator> { AuthNavigatorImpl() }
}