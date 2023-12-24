package com.example.app.service

import com.example.app.model.MusicalInstruments
import com.example.app.repository.MusicalInstrumentsRepository
import kotlinx.coroutines.flow.Flow

class MusicalInstrumentsService(private val repository: MusicalInstrumentsRepository) {

    val allMusicalInstruments: Flow<List<MusicalInstruments>> = repository.allMusicalInstruments

    fun addMusicalInstrument(newMusicalInstrument: MusicalInstruments) {
        repository.addMusicalInstrument(newMusicalInstrument)
    }

    fun insertAll(musicalInstrumentsList: List<MusicalInstruments>){
        musicalInstrumentsList.forEach {
            repository.addMusicalInstrument(it);
        }
    }

    fun deleteMusicalInstrument(musicalInstrument: MusicalInstruments) {
        repository.deleteMusicalInstrument(musicalInstrument)
    }

    fun updateMusicalInstrument(musicalInstrument: MusicalInstruments) {
        repository.updateMusicalInstrument(musicalInstrument)
    }

    fun getMusicalInstrumentsByInstrumentCategory(categoryID: Int): Flow<List<MusicalInstruments>> {
        return repository.getMusicalInstrumentsByInstrumentCategory(categoryID)
    }

    fun getMusicalInstrumentsByBrand(brandID: Int): Flow<List<MusicalInstruments>> {
        return repository.getMusicalInstrumentsByBrand(brandID)
    }

    fun getMusicalInstrumentsBySale(): Flow<List<MusicalInstruments>> {
        return repository.getMusicalInstrumentsBySale()
    }

    suspend fun getMusicalInstrumentByID(id: Int): MusicalInstruments? {
        return repository.getMusicalInstrumentByID(id)
    }
}