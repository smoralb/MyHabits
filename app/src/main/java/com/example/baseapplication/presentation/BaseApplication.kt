package com.example.baseapplication.presentation

import android.app.Application
import com.example.baseapplication.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(presentationModule)
        }
    }
}