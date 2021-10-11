package com.notes.notes.settings.data

import com.notes.notes.settings.presenter.Repository

class DataSettings : Repository {

    override fun saveTheme(isChecked: Boolean) {
        println("save theme ${isChecked}")
    }

    override fun saveLanguage(isChecked: Boolean) {
        println("save language ${isChecked}")
        if (isChecked) {

        } else {

        }
    }
}