package com.devs.mndy.views.screens.onboarding

import com.devs.mndy.R

data class OnboardingPage(
    val icon: Int,
    val title: String,
    val description: String
)

val onboardingPages = listOf(
    OnboardingPage(
        icon = R.drawable.ic_search,
        title = "Find Trusted Services",
        description = "Connect with verified professionals near you",
    ),
    OnboardingPage(
        icon = R.drawable.ic_location,
        title = "Track in Real-time",
        description = "Stay updated on your service status"
    ),
    OnboardingPage(
        icon = R.drawable.ic_wallet,
        title = "Pay Securely",
        description = "Multiple payment options available"
    ),
    OnboardingPage(
        icon = R.drawable.ic_calendar,
        title = "Book with Ease",
        description = "Simple booking, flexible scheduling"
    )
)
