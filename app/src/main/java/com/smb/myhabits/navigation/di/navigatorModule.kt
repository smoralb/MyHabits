package com.smb.myhabits.navigation.di

import com.smb.ft_auth.navigation.AuthNavigator
import com.smb.myhabits.com.smb.myhabits.navigation.AuthNavigatorImpl
import org.koin.dsl.module

val navigatorModule = module {
    single<AuthNavigator> { AuthNavigatorImpl() }
}