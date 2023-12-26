package com.example.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.Orders
import com.example.app.model.PendingOperations
import kotlinx.coroutines.flow.Flow

@Dao
interface PendingOperationsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operation: PendingOperations)

    @Update
    suspend fun update(operation: PendingOperations)

    @Delete
    suspend fun delete(operation: PendingOperations)

    @Query("SELECT * FROM PendingOperations WHERE pendingOperationID = :id")
    suspend fun getPendingOperationById(id: Int): PendingOperations?

    @Query("SELECT pendingOperationID FROM PendingOperations")
    fun getAllPendingOperationsIds(): List<Int>

    @Query("SELECT * FROM PendingOperations")
    fun getAllPendingOperations(): Flow<List<PendingOperations>>
}