package com.notes.notes.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ModelDao {

    @Query("SELECT * FROM ModelListItems")
    fun getAllList(): Flow<List<ModelListItems>>

    @Query("SELECT * FROM ModelListItems")
    fun getAll() : MutableList<ModelListItems>

    @Query("SELECT * FROM ModelListItems WHERE id = :id")
    fun getById(id: Long) : ModelListItems

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(modelListItems: ModelListItems)

    @Update
    fun update(modelListItems: ModelListItems)

    @Delete
    fun delete(modelListItems: ModelListItems)

    @Query("DELETE FROM ModelListItems")
    suspend fun deleteAll()
}