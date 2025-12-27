package com.devs.mndy.views.screens.languages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devs.mndy.R
import com.devs.mndy.utils.colors.mndyTheme

@Composable
fun LanguageCard(
    language: LanguageItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val borderColor =
        if (selected) mndyTheme.colors.primary
        else mndyTheme.colors.onBackground.copy(alpha = 0.3f)

    val background =
        if (selected) mndyTheme.colors.primary.copy(alpha = 0.08f)
        else mndyTheme.colors.background

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(background)
            .border(1.5.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.wrapContentHeight()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = language.flag,
                    fontSize = 22.sp
                )

                Spacer(Modifier.weight(1f))

                if (selected) {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = mndyTheme.colors.primary
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = language.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = language.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }
    }
}

@Preview
@Composable
private fun LanguageCardPreview(){
    LanguageCard(
        language = LanguageItem("hi", "हिंदी", "Hindi", "🇮🇳"),
        selected = true,
        onClick = {}
    )
}