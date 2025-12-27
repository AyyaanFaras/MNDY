package com.devs.mndy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.devs.mndy.utils.colors.MndyTheme
import com.devs.mndy.views.screens.SplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MndyTheme(darkTheme = isSystemInDarkTheme()) {
                SplashScreen()
            }
        }
    }
}