package com.example.baseapplication

import android.app.Application
import com.example.baseapplication.data.di.dataModule
import com.example.baseapplication.data.di.sampleDataModule
import com.example.baseapplication.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(presentationModule, dataModule, sampleDataModule))
        }
    }
}