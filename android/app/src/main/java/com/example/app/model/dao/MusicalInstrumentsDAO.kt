package com.example.app.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.MusicalInstruments
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicalInstrumentsDAO {
    @Insert
    suspend fun insert(musicalInstrument: MusicalInstruments)

    @Update
    suspend fun update(musicalInstrument: MusicalInstruments)

    @Delete
    suspend fun delete(musicalInstrument: MusicalInstruments)

    @Query("SELECT * FROM MusicalInstruments WHERE musicalInstrumentID = :id")
    suspend fun getInstrumentById(id: Int): MusicalInstruments?

    @Query("SELECT * FROM MusicalInstruments")
    fun getAllInstruments(): Flow<List<MusicalInstruments>>

    @Query("SELECT * FROM MusicalInstruments WHERE instrumentCategoryID = :categoryId")
    fun getInstrumentsByCategory(categoryId: Int): Flow<List<MusicalInstruments>>

    @Query("SELECT * FROM MusicalInstruments WHERE instrumentBrandID = :brandId")
    fun getInstrumentsByBrand(brandId: Int): Flow<List<MusicalInstruments>>
}