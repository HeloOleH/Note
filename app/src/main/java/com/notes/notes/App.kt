package com.notes.notes

import android.app.Application
import androidx.fragment.app.ListFragment
import com.notes.notes.database.ListRepository
import com.notes.notes.database.di.moduleDatabase
import com.notes.notes.edits.fragment.EditsFragment
import com.notes.notes.edits.viewmodel.ViewModelEdits
import com.notes.notes.lists.viewmodel.ListViewModel
import com.notes.notes.settings.fragment.SettingsFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(getModules())
        }
    }

    private val appModule = module {

        viewModel { ListViewModel(get()) }
        single { ListRepository(get()) }

        viewModel { ViewModelEdits(get()) }

        single { ListFragment() }
        single { EditsFragment() }
        single { SettingsFragment() }
    }

    private fun getModules() = listOf(
        appModule,
        moduleDatabase
    )
}