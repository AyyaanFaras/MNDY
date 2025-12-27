package com.devs.mndy.views.screens.languages

data class LanguageItem(
    val code: String,
    val title: String,
    val subtitle: String,
    val flag: String // emoji for now
)

val languages = listOf(
    LanguageItem("en", "English", "English", "🇬🇧"),
    LanguageItem("hi", "हिंदी", "Hindi", "🇮🇳"),
    LanguageItem("ta", "தமிழ்", "Tamil", "🇮🇳"),
    LanguageItem("te", "తెలుగు", "Telugu", "🇮🇳"),
    LanguageItem("kn", "ಕನ್ನಡ", "Kannada", "🇮🇳"),
    LanguageItem("mr", "मराठी", "Marathi", "🇮🇳")
)