package com.example.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDAO {
    @Insert
    suspend fun insert(user: Users)

    @Update
    suspend fun update(user: Users)

    @Delete
    suspend fun delete(user: Users)

    @Query("SELECT * FROM Users WHERE userID = :id")
    suspend fun getUserById(id: Int): Users?

    @Query("SELECT * FROM Users WHERE username = :username")
    suspend fun getUserByUsername(username: String): Users?

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<Users>>
}