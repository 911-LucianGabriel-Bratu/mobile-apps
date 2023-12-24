package com.example.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.InstrumentCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface InstrumentCategoriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(instrumentCategory: InstrumentCategories)

    @Delete
    suspend fun delete(instrumentCategory: InstrumentCategories)

    @Update
    suspend fun update(instrumentCategory: InstrumentCategories)

    @Query("SELECT * FROM InstrumentCategories ORDER BY instrumentCategoryName")
    fun getInstrumentCategories(): Flow<List<InstrumentCategories>>

    @Query("SELECT * FROM InstrumentCategories WHERE instrumentCategoryID = :id")
    suspend fun getInstrumentCategoryByID(id: Int): InstrumentCategories?
}