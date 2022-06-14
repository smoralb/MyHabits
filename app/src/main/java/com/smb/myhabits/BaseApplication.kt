package com.smb.myhabits

import android.app.Application
import com.smb.ft_auth.domain.di.domainAuthModule
import com.smb.ft_auth.presentation.di.presentationLoginModule
import com.smb.myhabits.data.di.sampleDataModule
import com.smb.myhabits.domain.di.baseDomainModule
import com.smb.myhabits.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@BaseApplication)
            modules(listOf(presentationModule, sampleDataModule, baseDomainModule, presentationLoginModule, domainAuthModule))
        }
    }
}