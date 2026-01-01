package com.devs.mndy.di

import com.devs.mndy.data.UserPreferences
import org.koin.androidx.compose.get
import org.koin.dsl.module

val appModule = module {
    // Provide UserPreferences as a singleton
    single { UserPreferences(get()) }
}
