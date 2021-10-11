package com.notes.notes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ModelListItems(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val body: String
)