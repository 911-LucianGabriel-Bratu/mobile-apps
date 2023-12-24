package com.example.app.service

import com.example.app.model.InstrumentBrands
import com.example.app.repository.InstrumentBrandsRepository
import kotlinx.coroutines.flow.Flow

class InstrumentBrandsService(private val repository: InstrumentBrandsRepository) {

    val allInstrumentBrands: Flow<List<InstrumentBrands>> = repository.allInstrumentBrands

    fun addInstrumentBrand(newInstrumentBrand: InstrumentBrands) {
        repository.addInstrumentBrand(newInstrumentBrand)
    }

    fun insertAll(instrumentBrandsList: List<InstrumentBrands>){
        instrumentBrandsList.forEach {
            repository.addInstrumentBrand(it);
        }
    }

    fun deleteInstrumentBrand(instrumentBrand: InstrumentBrands) {
        repository.deleteInstrumentBrand(instrumentBrand)
    }

    fun updateInstrumentBrand(instrumentBrand: InstrumentBrands) {
        repository.updateInstrumentBrand(instrumentBrand)
    }

    suspend fun getInstrumentBrandByID(id: Int): InstrumentBrands? {
        return repository.getInstrumentBrandByID(id)
    }
}