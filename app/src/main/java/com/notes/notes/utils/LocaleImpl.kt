package com.notes.notes.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.util.Log
import java.util.*

fun setLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    var resources: Resources = context.resources
    val configuration: Configuration = resources.configuration

//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    configuration.setLocale(locale)
//    } else {
//        configuration.locale = locale
//    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.createConfigurationContext(configuration)
    }
    //resources = activity.resources

    //resources.updateConfiguration(configuration, resources.displayMetrics)

    Log.d(TAG, "setLocale: ${locale.language} ### $locale ### ${locale.getDisplayLanguage()}")
}