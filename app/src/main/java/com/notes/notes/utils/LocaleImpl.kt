package com.notes.notes.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import java.util.*

fun setLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    var resources: Resources = context.resources
    val configuration: Configuration = resources.configuration

    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)

    Log.d(TAG, "setLocale: ${locale.language} ### $locale ### ${locale.getDisplayLanguage()}")
}