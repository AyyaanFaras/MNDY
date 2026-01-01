package com.devs.mndy.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class InitKoin : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin events to Android logcat
            androidLogger()
            // Reference the Android context
            androidContext(this@InitKoin)
            // Load your modules
//            modules(appModule)
        }
    }
}
