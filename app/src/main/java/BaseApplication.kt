package com.smb.myhabits

import android.app.Application
import com.smb.ft_auth.data.di.dataAuthModule
import com.smb.ft_auth.domain.di.domainAuthModule
import com.smb.ft_auth.presentation.di.presentationAuthModule
import com.smb.ft_main.data.di.sampleDataModule
import com.smb.ft_main.domain.di.baseDomainModule
import com.smb.ft_main.presentation.di.presentationModule
import com.smb.navigator.di.navigation.di.navigatorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    presentationModule,
                    sampleDataModule,
                    baseDomainModule,
                    presentationAuthModule,
                    domainAuthModule,
                    dataAuthModule,
                    navigatorModule
                )
            )
        }
    }
}