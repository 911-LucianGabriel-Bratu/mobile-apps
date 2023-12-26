package com.example.app.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Orders::class, parentColumns = ["orderID"], childColumns = ["orderID"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class PendingOperations (
    @PrimaryKey(autoGenerate = true) val pendingOperationID: Int,
    val orderID: Int,
    val operationType: String
)