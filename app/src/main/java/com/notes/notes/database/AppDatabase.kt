package com.notes.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ModelListItems::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getModelDao(): ModelDao
}