package com.devs.mndy.views.screens.languages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devs.mndy.R
import com.devs.mndy.utils.colors.MndyTheme
import com.devs.mndy.utils.colors.mndyTheme
import com.devs.mndy.views.components.PrimaryButton

@Composable
fun LanguageSelectionScreen(
    languages: List<LanguageItem>,
    selectedCode: String,
    onLanguageSelected: (String) -> Unit,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mndyTheme.colors.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(48.dp))

        // Icon
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    mndyTheme.colors.primary.copy(alpha = 0.12f),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_language),
                contentDescription = null,
                tint = mndyTheme.colors.primary
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Choose Your Language",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Select your preferred language",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(32.dp))

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(languages) { language ->
                LanguageCard(
                    language = language,
                    selected = language.code == selectedCode,
                    onClick = { onLanguageSelected(language.code) }
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        PrimaryButton(
            text = "Continue",
            onClick = onContinue
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun LanguageSelectionPreview() {
    val languages = listOf(
        LanguageItem("en", "English", "English", "🇬🇧"),
        LanguageItem("hi", "हिंदी", "Hindi", "🇮🇳"),
        LanguageItem("ta", "தமிழ்", "Tamil", "🇮🇳"),
        LanguageItem("te", "తెలుగు", "Telugu", "🇮🇳"),
        LanguageItem("kn", "ಕನ್ನಡ", "Kannada", "🇮🇳"),
        LanguageItem("mr", "मराठी", "Marathi", "🇮🇳")
    )

    MndyTheme {
        LanguageSelectionScreen(
            languages = languages,
            selectedCode = "en",
            onLanguageSelected = {},
            onContinue = {}
        )
    }
}

