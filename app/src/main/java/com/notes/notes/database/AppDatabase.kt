package com.notes.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ModelListItems::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getModelDao(): ModelDao

    companion object {
        private const val DATABASE_NAME = "app_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            coroutineScope: CoroutineScope
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDatabaseCallBack(coroutineScope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class AppDatabaseCallBack(
            private val coroutineScope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { appDatabase ->
                    coroutineScope.launch(Dispatchers.IO) {
                        populateDatabase(appDatabase.getModelDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(modelDao: ModelDao) {
            modelDao.deleteAll()

            val lists = ModelListItems(1, "title sample", "sample body")
            modelDao.insert(lists)
        }
    }
}