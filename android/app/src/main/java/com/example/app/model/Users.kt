package com.example.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class Users(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userID") val userID: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "userPassword") val userPassword: String
) {
}