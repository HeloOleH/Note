package com.notes.notes.settings.presenter

interface Repository {
    fun saveTheme(isChecked: Boolean)
    fun saveLanguage(isChecked: Boolean)
}