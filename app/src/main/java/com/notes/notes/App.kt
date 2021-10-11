package com.notes.notes

import android.app.Application
import com.notes.notes.database.AppDatabase
import com.notes.notes.database.ListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ListRepository(database.getModelDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}