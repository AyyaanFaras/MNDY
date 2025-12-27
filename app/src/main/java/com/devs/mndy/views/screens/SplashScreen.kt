package com.devs.mndy.views.screens

import android.window.SplashScreen
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devs.mndy.R
import com.devs.mndy.utils.colors.GreenDark
import com.devs.mndy.utils.colors.GreenPrimary
import com.devs.mndy.utils.colors.YellowAccent
import com.devs.mndy.views.components.PageIndicator

@Composable
fun SplashScreen(modifier: Modifier = Modifier){
    SplashContent(modifier)
}

@Composable
private fun SplashContent(
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
        SplashScreen()
    }
}
