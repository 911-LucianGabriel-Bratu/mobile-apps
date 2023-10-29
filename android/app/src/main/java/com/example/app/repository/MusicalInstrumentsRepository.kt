package com.example.app.repository

import com.example.app.model.MusicalInstruments
import com.example.app.model.dao.MusicalInstrumentsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MusicalInstrumentsRepository(private val musicalInstrumentsDao: MusicalInstrumentsDAO) {
    val allMusicalInstruments: Flow<List<MusicalInstruments>> = musicalInstrumentsDao.getAllInstruments()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addMusicalInstrument(newMusicalInstrument: MusicalInstruments){
        coroutineScope.launch(Dispatchers.IO) {
            musicalInstrumentsDao.insert(newMusicalInstrument)
        }
    }

    fun deleteMusicalInstrument(musicalInstrument: MusicalInstruments){
        coroutineScope.launch(Dispatchers.IO) {
            musicalInstrumentsDao.delete(musicalInstrument)
        }
    }

    fun updateMusicalInstrument(musicalInstrument: MusicalInstruments){
        coroutineScope.launch(Dispatchers.IO) {
            musicalInstrumentsDao.update(musicalInstrument)
        }
    }

    fun getMusicalInstrumentsByInstrumentCategory(categoryID: Int): Flow<List<MusicalInstruments>> {
        return musicalInstrumentsDao.getInstrumentsByCategory(categoryID)
    }

    fun getMusicalInstrumentsByBrand(brandID: Int): Flow<List<MusicalInstruments>> {
        return musicalInstrumentsDao.getInstrumentsByBrand(brandID)
    }

    fun getMusicalInstrumentsBySale(): Flow<List<MusicalInstruments>> {
        return musicalInstrumentsDao.getInstrumentsOnSale()
    }

    suspend fun getMusicalInstrumentByID(id: Int): MusicalInstruments? {
            return musicalInstrumentsDao.getInstrumentById(id)
    }
}