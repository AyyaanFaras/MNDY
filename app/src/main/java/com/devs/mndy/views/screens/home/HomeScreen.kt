package com.devs.mndy.views.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devs.mndy.data.UserProfile
import com.devs.mndy.utils.colors.mndyTheme

@Composable
fun HomeScreen(profile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9F8))
    ) {
        // Simple Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(mndyTheme.colors.primary)
                .padding(top = 48.dp, bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Dashboard", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Column(modifier = Modifier.padding(24.dp)) {
            // Profile Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(mndyTheme.colors.primary.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (profile.name.isNotEmpty()) profile.name.take(1).uppercase() else "U",
                            color = mndyTheme.colors.primary,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(text = profile.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(text = "Language: ${profile.language.uppercase()}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)

                    Spacer(Modifier.height(24.dp))
                    Divider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(Modifier.height(24.dp))

                    // Detail Rows
                    InfoRow(icon = Icons.Default.Phone, label = "Phone", value = profile.phone)
                    InfoRow(icon = Icons.Default.Email, label = "Email", value = profile.email.ifEmpty { "Not provided" })
                    InfoRow(icon = Icons.Default.LocationOn, label = "Address", value = "${profile.district}, ${profile.state}")
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = mndyTheme.colors.primary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(12.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}
