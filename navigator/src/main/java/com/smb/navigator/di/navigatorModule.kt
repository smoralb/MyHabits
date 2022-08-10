package com.smb.navigator.di

import com.smb.ft_auth.navigation.LandingNavigator
import com.smb.navigator.LandingNavigatorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val navigatorModule = module {
    single<LandingNavigator> { LandingNavigatorImpl(androidContext()) }
}