package com.android.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

private const val USER_PREFERENCES_NAME = "user_preferences_name"

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun setAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN] = token
        }
    }

    val accessToken: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.ACCESS_TOKEN] ?: ""
        }

    suspend fun clearDataStore() {
        dataStore.edit { pref ->
            pref.clear()
        }
    }

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }
}
