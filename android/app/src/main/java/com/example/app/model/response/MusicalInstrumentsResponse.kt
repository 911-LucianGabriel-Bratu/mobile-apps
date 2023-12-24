package com.example.app.model.response

data class MusicalInstrumentsResponse (
    val musicalInstrumentID: Int,
    val instrumentCategoryID: Int,
    val instrumentBrandID: Int,
    val musicalInstrumentName: String,
    val description: String,
    val pngUrl: String,
    val price: Float,
    val quantity: Int,
    val onSale: Boolean
)