package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InstrumentBrands(
    @PrimaryKey(autoGenerate = true) val instrumentBrandID: Int,
    val instrumentBrandName: String,
    val pngUrl: String
) {
}