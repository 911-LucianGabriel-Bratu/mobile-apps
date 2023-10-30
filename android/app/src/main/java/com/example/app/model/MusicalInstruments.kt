package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
        ForeignKey(entity = InstrumentCategories::class, parentColumns = ["instrumentCategoryID"], childColumns = ["instrumentCategoryID"], onDelete = CASCADE),
        ForeignKey(entity = InstrumentBrands::class, parentColumns = ["instrumentBrandID"], childColumns = ["instrumentBrandID"], onDelete = CASCADE)
])
data class MusicalInstruments(
    @PrimaryKey(autoGenerate = true) val musicalInstrumentID: Int,
    val instrumentCategoryID: Int,
    val instrumentBrandID: Int,
    val musicalInstrumentName: String,
    val description: String,
    val pngUrl: String,
    val price: Float,
    val quantity: Int,
    val onSale: Boolean
) {
}