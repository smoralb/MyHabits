package com.smb.myhabits

import android.app.Application
import com.smb.core.data.di.dataModule
import com.smb.myhabits.data.di.sampleDataModule
import com.smb.myhabits.domain.di.baseDomainModule
import com.smb.myhabits.presentation.di.presentationModule
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