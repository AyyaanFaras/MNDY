package com.devs.mndy.views.screens

import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.offset
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devs.mndy.R
import com.devs.mndy.data.UserPreferences
import com.devs.mndy.data.UserProfile
import com.devs.mndy.utils.colors.GreenDark
import com.devs.mndy.utils.colors.GreenPrimary
import com.devs.mndy.utils.colors.MndyTheme
import com.devs.mndy.utils.colors.YellowAccent
import com.devs.mndy.views.components.PageIndicator
import com.devs.mndy.views.screens.home.HomeScreen
import com.devs.mndy.views.screens.languages.LanguageSelectionScreen
import com.devs.mndy.views.screens.languages.languages
import com.devs.mndy.views.screens.onboarding.PhoneInputScreen
import com.devs.mndy.views.screens.otp.VerifyOtpScreen
import com.devs.mndy.views.screens.profile.CompleteProfileScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val userPrefs: UserPreferences = koinInject()
//            val userProfile by userPrefs.userProfile.collectAsState(initial = null)
    val userProfile by userPrefs.userProfile.collectAsState(initial = null)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    MndyTheme {
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                SplashContent()

                LaunchedEffect(userProfile) {
                    if (userProfile != null) {
                        delay(2000)
                        if (userProfile?.isLoggedIn == true) {
                            navController.navigate("home") {
                                popUpTo("splash") { inclusive = true }
                            }
                        } else {
                            navController.navigate("onboarding") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    }
                }
            }

            composable("onboarding") {
                var onboardingStep by remember { mutableStateOf("language") }
                var selectedLanguage by remember { mutableStateOf("en") }

                when(onboardingStep){
                    "language" ->{
                        LanguageSelectionScreen(
                            languages = languages,
                            selectedCode = selectedLanguage,
                            onLanguageSelected = { selectedLanguage = it },
                            onContinue = {
                                scope.launch {
                                    // Save selected language to UserPrefs
                                    val current = userProfile ?: UserProfile()
                                    userPrefs.saveProfile(current.copy(language = selectedLanguage))
                                    onboardingStep = "phone"
                                }
                            }
                        )
                    }
                    "phone" ->{
                        PhoneInputScreen(
                            onSendOtp = { phone ->
                                navController.navigate("verify/$phone")
                            }
                        )
                    }
                }

            }
            composable("verify/{phone}") { backStack ->
                val phone = backStack.arguments?.getString("phone") ?: ""
                var isOtpError by remember { mutableStateOf(false) }
                val context = LocalContext.current

                VerifyOtpScreen(
                    phoneNumber = phone,
                    isError = isOtpError,
                    onVerifyChange = { isOtpError = false }, // Clear error when user types
                    onVerifyClick = { otp ->
                        if (otp == "123456") {
                            scope.launch {
                                // 1. Save phone to DataStore
                                val current = userProfile ?: UserProfile()
                                userPrefs.saveProfile(current.copy(phone = phone))

                                // 2. Navigate to Profile
                                navController.navigate("complete_profile/$phone")
                            }
                        } else {
                            isOtpError = true
                            Toast.makeText(
                                context,
                                "Incorrect otp, try again with 123456",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }

            composable("complete_profile/{phone}") { backStack ->
                val phone = backStack.arguments?.getString("phone") ?: ""
                CompleteProfileScreen(
                    initialProfile = userProfile,
                    onSaveProfile = { name, email, phone, district, state ->
                        scope.launch {
                            val current = userProfile ?: UserProfile()
                            userPrefs.saveProfile(current.copy(
                                name = name,
                                email = email,
                                phone = phone,
                                isLoggedIn = true,
                                district = district,
                                state = state
                            ))
                            navController.navigate("home") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    }
                )
            }

//            composable("home") {
//                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text("Hello World")
//                }
//            }
            composable("home") {
                // userProfile is already collected at the top of AppNavigation
                if (userProfile != null) {
                    HomeScreen(profile = userProfile!!)
                }
            }
        }
    }
}

@Composable
fun SplashContent(modifier: Modifier = Modifier) {
    // Isolated Logo Animation: Increased speed (600ms) and distance (6dp)
    val infiniteTransition = rememberInfiniteTransition(label = "logo_float")
    val logoOffset by infiniteTransition.animateFloat(
        initialValue = -4f, // Doubled from 3
        targetValue = 4f,    // Doubled from 3
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing), // Increased speed from 1000 to 600
            repeatMode = RepeatMode.Reverse
        ), label = "offset"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(GreenPrimary, GreenDark)))
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center), // Removed offset from here
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Apply the offset ONLY to the logo
            Box(modifier = Modifier.offset(y = logoOffset.dp)) {
                SplashLogo()
            }

            Spacer(Modifier.height(24.dp))
            AppTitle()
            Spacer(Modifier.height(8.dp))
            AppTagline()
        }

        AnimatedLoading(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}


@Composable
fun AnimatedLoading(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(3) { index ->
            // Create a vertical offset animation for each dot
            val dotOffset by infiniteTransition.animateFloat(
                initialValue = -2f, // Matches the logo's starting height
                targetValue = 2f,   // Matches the logo's ending height
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = index * 150, // Stagger: 0ms, 150ms, 300ms
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ), label = "dot_offset"
            )

            Box(
                Modifier
                    .size(8.dp)
                    // Apply the vertical movement
                    .offset(y = dotOffset.dp)
                    .clip(CircleShape)
                    // Keep them fully opaque or slightly transparent as per design
                    .background(Color.White.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
private fun SplashContent2(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        GreenPrimary,
                        GreenDark
                    )
                )
            )
            .padding(horizontal = 24.dp)
    ) {

        // Center content
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SplashLogo()

            Spacer(modifier = Modifier.height(24.dp))

            AppTitle()

            Spacer(modifier = Modifier.height(8.dp))

            AppTagline()
        }

        // Bottom indicators
        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            totalDots = 3,
            selectedIndex = 1
        )
    }
}

@Composable
private fun SplashLogo() {
    Box(
        modifier = Modifier
            .size(96.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_leaf),
            contentDescription = "App Logo",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}


@Composable
private fun AppTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "MNDY",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "+",
            style = MaterialTheme.typography.headlineLarge,
            color = YellowAccent,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun AppTagline() {
    Text(
        text = "Services Made Simple",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White.copy(alpha = 0.85f)
    )
}


@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    MaterialTheme {
        AppNavigation()
    }
}
