package com.example.app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class PendingOperations (
    @PrimaryKey(autoGenerate = true) val pendingOperationID: Int,
    val orderID: Int,
    val operationType: String
)