package com.example.baseapplication

import android.app.Application
import com.example.baseapplication.data.di.sampleDataModule
import com.example.baseapplication.domain.di.baseDomainModule
import com.example.baseapplication.presentation.di.presentationModule
import com.example.core.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * TODO: this should be moved to String extension file when is created
 */
const val EMPTY_STRING = ""

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