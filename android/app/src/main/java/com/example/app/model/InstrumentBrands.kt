package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "InstrumentBrands")
data class InstrumentBrands(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "instrumentBrandID") val instrumentBrandID: Int,
    @ColumnInfo(name = "instrumentBrandName") val instrumentBrandName: String
) {
}