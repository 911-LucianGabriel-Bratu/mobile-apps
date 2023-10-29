package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(foreignKeys = [
        ForeignKey(entity = MusicalInstruments::class, parentColumns = ["musicalInstrumentID"], childColumns = ["musicalInstrumentID"], onDelete = CASCADE),
        ForeignKey(entity = Users::class, parentColumns = ["userID"], childColumns = ["userID"], onDelete = CASCADE)]
    )
data class Orders(
    @PrimaryKey(autoGenerate = true)val orderID: Int,
    val musicalInstrumentID: Int,
    val userID: Int,
    val orderedAt: Date,
    val quantity: Int,
    val totalPrice: Float,
    val deleted: Boolean
) {
}