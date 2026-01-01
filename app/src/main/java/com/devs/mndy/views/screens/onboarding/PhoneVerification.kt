package com.devs.mndy.views.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.devs.mndy.utils.colors.mndyTheme
import com.devs.mndy.views.components.PrimaryButton

@Composable
fun PhoneInputScreen(
    onSendOtp: (String) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mndyTheme.colors.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(Modifier.height(48.dp))

        // Phone Icon in Circle
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(mndyTheme.colors.primary.copy(alpha = 0.1f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Phone",
                tint = mndyTheme.colors.primary,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Enter Mobile Number",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B3D2E) // Darker green/black text
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "We will send you an OTP to verify your number",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Phone Number",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Custom Input Field as per image
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                .background(Color(0xFFF9F9F9), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "IN  +91",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(16.dp))

            BasicTextField(
                value = phoneNumber,
                onValueChange = { if (it.length <= 10) phoneNumber = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (phoneNumber.isEmpty()) {
                        Text(
                            text = "Enter 10-digit number",
                            color = Color.Gray.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Send OTP",
            enabled = phoneNumber.length == 10,
            onClick = { onSendOtp(phoneNumber) }
        )

        Spacer(Modifier.height(24.dp))
    }
}
