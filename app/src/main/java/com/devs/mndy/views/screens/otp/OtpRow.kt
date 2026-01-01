package com.devs.mndy.views.screens.otp

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.requestFocus
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.devs.mndy.utils.colors.mndyTheme

@Composable
fun OtpRow(
    otp: String,
    otpLength: Int,
    onOtpChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    Box(contentAlignment = Alignment.Center) {
        // Visible Boxes
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.clickable(
                interactionSource = null,
                indication = null
            ) { focusRequester.requestFocus() }
        ) {
            repeat(otpLength) { index ->
                OtpBox(
                    value = otp.getOrNull(index)?.toString() ?: "",
                    isFocused = otp.length == index
                )
            }
        }

        // Hidden text field for keyboard input
        BasicTextField(
            value = otp,
            onValueChange = {
                if (it.length <= otpLength) onOtpChange(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            modifier = Modifier
                .size(1.dp) // Minimum size but must be visible for focus
                .focusRequester(focusRequester)
        )
    }
}

@Composable
fun OtpBox(
    value: String,
    isFocused: Boolean
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.5.dp,
                color = if (isFocused)
                    mndyTheme.colors.primary
                else
                    mndyTheme.colors.primary.copy(alpha = 0.4f),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}
