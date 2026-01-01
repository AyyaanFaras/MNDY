package com.devs.mndy

import android.app.Application
import com.devs.mndy.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MndyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MndyApplication)
            modules(appModule)
        }
    }
}
