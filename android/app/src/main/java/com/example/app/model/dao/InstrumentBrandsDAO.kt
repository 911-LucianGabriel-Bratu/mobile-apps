package com.example.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.InstrumentBrands
import kotlinx.coroutines.flow.Flow

@Dao
interface InstrumentBrandsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(instrumentBrand: InstrumentBrands)

    @Delete
    suspend fun delete(instrumentBrand: InstrumentBrands)

    @Query("SELECT * FROM InstrumentBrands ORDER BY instrumentBrandName")
    fun getInstrumentBrandsOrderedByName(): Flow<List<InstrumentBrands>>

    @Query("SELECT * FROM InstrumentBrands WHERE instrumentBrandID = :id")
    suspend fun getInstrumentBrandByID(id: Int): InstrumentBrands?

    @Update
    suspend fun update(instrumentBrand: InstrumentBrands)
}