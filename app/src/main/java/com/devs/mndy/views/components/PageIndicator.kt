package com.devs.mndy.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devs.mndy.utils.colors.GreenPrimary
import com.devs.mndy.utils.colors.mndyTheme

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        mndyTheme.colors.onBackground.copy(alpha = 0.4f)
                    )
            )
        }
    }
}
