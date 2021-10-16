package com.notes.notes

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.notes.notes.utils.PREFERENCES_KEY_IS_CHECKED_LANGUAGE
import com.notes.notes.utils.PREFERENCES_KEY_IS_CHECKED_THEME
import com.notes.notes.utils.readPreferences
import com.notes.notes.utils.setLocale
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val isChecked = readPreferences(this, PREFERENCES_KEY_IS_CHECKED_THEME)
//        val isCheckedLanguage = readPreferences(this, PREFERENCES_KEY_IS_CHECKED_LANGUAGE)
//
//        if (isChecked) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        }
//
//        if (isCheckedLanguage) {
//            setLocale(this, "en")
//        } else {
//            setLocale(this, "uk")
//        }

        Log.d(com.notes.notes.utils.TAG, "locale act : ${Locale.getDefault()} ${Locale.getAvailableLocales()}" +
                "${Locale.getDefault().getDisplayLanguage()}")
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
    }

    //override fun attachBaseContext(newBase: Context) {
    //    super.attachBaseContext(CustomContextWrapper.wrap(newBase, "ua"))
    //}
}
