package com.notes.notes.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ListRepository(private val modelDao: ModelDao) {

    val allModels: Flow<List<ModelListItems>> = modelDao.getAllList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(modelListItems: ModelListItems) {
        modelDao.insert(modelListItems)
    }
}