package com.notes.notes.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

val PREFERENCES_KEY_IS_CHECKED_THEME = booleanPreferencesKey("is_checked_theme")
val PREFERENCES_KEY_IS_CHECKED_LANGUAGE = booleanPreferencesKey("is_checked_language")


fun readPreferences(
    context: Context,
    preferencesKey: Preferences.Key<Boolean>
): Boolean {
    val read = runBlocking { context.dataStore.data.first() }
    return read[preferencesKey] ?: true
}


fun writePreferences(
    context: Context,
    preferencesKey: Preferences.Key<Boolean>,
    newValue: Boolean
) {
    CoroutineScope(Dispatchers.IO).launch {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[preferencesKey] = newValue
        }
    }
}