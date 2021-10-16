package com.notes.notes

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.notes.notes.database.AppDatabase
import com.notes.notes.database.ListRepository
import com.notes.notes.utils.PREFERENCES_KEY_IS_CHECKED_LANGUAGE
import com.notes.notes.utils.PREFERENCES_KEY_IS_CHECKED_THEME
import com.notes.notes.utils.readPreferences
import com.notes.notes.utils.setLocale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ListRepository(database.getModelDao()) }

    override fun onCreate() {
        super.onCreate()

        val isChecked = readPreferences(this, PREFERENCES_KEY_IS_CHECKED_THEME)
        val isCheckedLanguage = readPreferences(this, PREFERENCES_KEY_IS_CHECKED_LANGUAGE)

        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        if (isCheckedLanguage) {
            setLocale(this, "en")
        } else {
            setLocale(this, "uk")
        }
    }
}