package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InstrumentCategories")
data class InstrumentCategories(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "instrumentCategoryID") val instrumentCategoryID: Int,
    @ColumnInfo(name = "instrumentCategoryName") val instrumentCategoryName: String
    ) {}