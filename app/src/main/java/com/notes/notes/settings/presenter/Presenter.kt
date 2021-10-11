package com.notes.notes.settings.presenter

interface Presenter {
    fun switchTheme(isChecked: Boolean)
    fun switchLanguage(isChecked: Boolean)
    fun onDestroy()
}