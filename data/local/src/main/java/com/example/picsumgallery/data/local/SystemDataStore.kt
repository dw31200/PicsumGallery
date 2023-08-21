package com.example.picsumgallery.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SystemDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val THEME_KEY = intPreferencesKey("theme")

    val getTheme = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[THEME_KEY] ?: 0
        }

    suspend fun setTheme(theme: Int) {
        context.dataStore.edit { settings ->
            settings[THEME_KEY] = theme
        }
    }
}
