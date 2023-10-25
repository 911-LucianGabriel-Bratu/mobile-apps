package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "MusicalInstruments",
    foreignKeys = [
        ForeignKey(entity = InstrumentCategories::class, parentColumns = ["instrumentCategoryID"], childColumns = ["instrumentCategoryID"], onDelete = CASCADE),
        ForeignKey(entity = InstrumentBrands::class, parentColumns = ["instrumentBrandID"], childColumns = ["instrumentBrandID"], onDelete = CASCADE)
])
data class MusicalInstruments(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "musicalInstrumentID") val musicalInstrumentID: Int,
    @ColumnInfo(name = "instrumentCategoryID") val instrumentCategoryID: Int,
    @ColumnInfo(name = "instrumentBrandID") val instrumentBrandID: Int,
    @ColumnInfo(name = "musicalInstrumentName") val musicalInstrumentName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "onSale") val onSale: Boolean
) {
}