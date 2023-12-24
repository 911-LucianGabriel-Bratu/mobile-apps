package com.example.app.model.response

import java.util.Date

data class OrdersResponse (
    val orderID: Int,
    val musicalInstrumentID: Int,
    val userID: Int,
    val orderedAt: Date,
    val quantity: Int,
    val totalPrice: Float,
    val deleted: Boolean
)