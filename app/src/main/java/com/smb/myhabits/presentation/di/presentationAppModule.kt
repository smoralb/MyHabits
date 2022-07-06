package com.smb.myhabits.com.smb.myhabits.presentation.di


import com.smb.myhabits.presentation.MainViewModel
import org.koin.dsl.module

val presentationAppModule = module {
    factory { MainViewModel(checkSessionUseCase = get()) }
}