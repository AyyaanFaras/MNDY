package com.devs.mndy.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

data class UserProfile(
    val name: String = "",
    val phone: String = "",
    val district: String = "",
    val state: String = "",
    val email: String = "",
    val language: String = "en",
    val isLoggedIn: Boolean = false
)

class UserPreferences(private val context: Context) {
    private val USER_PROFILE_KEY = stringPreferencesKey("user_profile")

    suspend fun saveProfile(profile: UserProfile) {
        context.dataStore.edit { prefs ->
            prefs[USER_PROFILE_KEY] = Gson().toJson(profile)
        }
    }

    val userProfile: Flow<UserProfile?> = context.dataStore.data.map { prefs ->
        val json = prefs[USER_PROFILE_KEY]
        if (json != null) Gson().fromJson(json, UserProfile::class.java)
        else UserProfile() // Return default empty profile
    }
}
