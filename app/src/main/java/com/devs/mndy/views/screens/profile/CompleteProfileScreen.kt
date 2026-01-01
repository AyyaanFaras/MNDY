package com.devs.mndy.views.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devs.mndy.R
import com.devs.mndy.data.UserProfile
import com.devs.mndy.utils.colors.mndyTheme
import com.devs.mndy.views.components.PrimaryButton

@Composable
fun CompleteProfileScreen(
    initialProfile: UserProfile?,
    onSaveProfile: (name: String, email: String, phone: String, district: String, state: String) -> Unit
) {
    var fullName by remember { mutableStateOf(initialProfile?.name?:"") }
    var email by remember { mutableStateOf(initialProfile?.email?:"") }
    var phoneNumber by remember { mutableStateOf(initialProfile?.phone?:"") }
    var district by remember { mutableStateOf(initialProfile?.district?:"") }
    var state by remember { mutableStateOf(initialProfile?.state?:"") }
    val selectedCategory = remember { mutableStateOf("Agriculture") }
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF8F9F8)) // Light off-white background
    ) {
        // Green Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(mndyTheme.colors.primary),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("U", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Icon(Icons.Default.PhotoCamera, contentDescription = null, modifier = Modifier.size(18.dp), tint = Color.Gray)
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text("Edit Profile", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Text("COMPLETE YOUR PROFILE", style = MaterialTheme.typography.labelLarge, color = Color.Gray)

            ProfileField(label = "Full Name", isMandatory = true, value = fullName, onValueChange = { fullName = it }, placeholder = "Enter your full name", icon = Icons.Default.Person)
            ProfileField(label = "Phone Number", value = phoneNumber, onValueChange = {}, placeholder = "", icon = Icons.Default.Phone, enabled = false)
            ProfileField(label = "Email", value = email, onValueChange = { email = it }, placeholder = "Enter your email", icon = Icons.Default.Email)

            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = mndyTheme.colors.primary, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("ADDRESS", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ProfileField(label = "District", isMandatory = true, value = district, onValueChange = { district = it }, placeholder = "Select district", modifier = Modifier.weight(1f))
                ProfileField(label = "State", isMandatory = true, value = state, onValueChange = { state = it }, placeholder = "Select state", modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(16.dp))
            Text("ALL CATEGORIES", style = MaterialTheme.typography.labelLarge, color = Color.Gray)

            CategoryCard("Agriculture", "Tractor Services", R.drawable.ic_leaf, selectedCategory)
            CategoryCard("Household", "Equipment Rental", Icons.Default.Home, selectedCategory)
            CategoryCard("Construction", "Transport", Icons.Default.Build, selectedCategory)

            Spacer(Modifier.height(32.dp))
            PrimaryButton(text = "Save Profile") {
                // Validation Logic
                if (fullName.trim().isEmpty() || district.trim().isEmpty() || state.trim().isEmpty()) {
                    android.widget.Toast.makeText(
                        context,
                        "Fill Mandatory fields",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Proceed with saving if valid
                    onSaveProfile(fullName, email, phoneNumber, district, state)
                }
            }
        }
    }
}

@Composable
fun ProfileField(
    label: String,
    isMandatory: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Any? = null,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Row {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            if (isMandatory) {
                Text(
                    text = " *",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Red
                )
            }
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = Color.Gray.copy(alpha = 0.5f)) },
            leadingIcon = {
                if (icon is androidx.compose.ui.graphics.vector.ImageVector) Icon(icon, contentDescription = null, tint = Color.Gray)
            },
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF1F1F1),
                focusedContainerColor = Color(0xFFF1F1F1),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = mndyTheme.colors.primary
            )
        )
    }
}

@Composable
fun CategoryCard(title: String, subtitle: String, icon: Any, selectedState: MutableState<String>) {
    val isSelected = selectedState.value == title
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                1.dp,
                if (isSelected) mndyTheme.colors.primary else Color.Transparent,
                RoundedCornerShape(16.dp)
            )
            .clickable { selectedState.value = title },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF1F1F1)), contentAlignment = Alignment.Center) {
                if (icon is Int) Icon(painterResource(icon), null, tint = mndyTheme.colors.primary, modifier = Modifier.size(24.dp))
                else if (icon is androidx.compose.ui.graphics.vector.ImageVector) Icon(icon, null, tint = mndyTheme.colors.primary, modifier = Modifier.size(24.dp))
            }
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            if (isSelected) {
                Icon(Icons.Default.CheckCircle, null, tint = mndyTheme.colors.primary)
            }
        }
    }
}
