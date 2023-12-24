package com.example.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.Orders
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Orders)

    @Update
    suspend fun update(order: Orders)

    @Delete
    suspend fun delete(order: Orders)

    @Query("SELECT * FROM Orders WHERE orderID = :id")
    suspend fun getOrderById(id: Int): Orders?

    @Query("SELECT * FROM Orders")
    fun getAllOrders(): Flow<List<Orders>>

    @Query("SELECT * FROM Orders WHERE userID = :userId")
    fun getOrdersByUser(userId: Int): Flow<List<Orders>>

    @Query("SELECT * FROM Orders WHERE musicalInstrumentID = :instrumentId")
    fun getOrdersByInstrument(instrumentId: Int): Flow<List<Orders>>
}