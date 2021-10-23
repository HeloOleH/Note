package com.notes.notes.database.di

import androidx.room.Room
import com.notes.notes.database.AppDatabase
import org.koin.dsl.module

private const val DATABASE_NAME = "app_database"

val moduleDatabase = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME).build() }
    single { get<AppDatabase>().getModelDao() }
}