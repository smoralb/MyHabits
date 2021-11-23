package com.example.habits

import android.app.Application
import com.example.habits.data.di.sampleDataModule
import com.example.habits.domain.di.baseDomainModule
import com.example.habits.presentation.di.presentationModule
import com.example.core.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(presentationModule, dataModule, sampleDataModule, baseDomainModule))
        }
    }
}