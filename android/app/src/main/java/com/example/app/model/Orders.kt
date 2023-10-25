package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Orders",
    foreignKeys = [
        ForeignKey(entity = MusicalInstruments::class, parentColumns = ["musicalInstrumentID"], childColumns = ["musicalInstrumentID"], onDelete = CASCADE),
        ForeignKey(entity = Users::class, parentColumns = ["userID"], childColumns = ["userID"], onDelete = CASCADE)]
    )
data class Orders(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "orderID") val orderID: Int,
    @ColumnInfo(name = "musicalInstrumentID") val musicalInstrumentID: Int,
    @ColumnInfo(name = "userID") val userID: Int,
    @ColumnInfo(name = "orderedAt") val orderedAt: Date,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "totalPrice") val totalPrice: Float,
    @ColumnInfo(name = "deleted") val deleted: Boolean
) {
}