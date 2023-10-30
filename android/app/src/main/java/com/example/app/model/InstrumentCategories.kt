package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstrumentCategories(
    @PrimaryKey(autoGenerate = true) val instrumentCategoryID: Int,
    val instrumentCategoryName: String,
    val pngUrl: String
    ) {}