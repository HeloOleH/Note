package com.notes.notes.settings.data

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.notes.notes.settings.presenter.Repository
import com.notes.notes.utils.setLocale

class DataSettings(val context: Context) : Repository {

    override fun saveTheme(isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun saveLanguage(isChecked: Boolean) {
        if (isChecked) {
            setLocale(context, "en")
        } else {
            setLocale(context, "uk")
        }
    }
}