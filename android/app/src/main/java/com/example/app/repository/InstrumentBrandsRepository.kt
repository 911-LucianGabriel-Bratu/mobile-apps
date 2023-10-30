package com.example.app.repository

import com.example.app.model.InstrumentBrands
import com.example.app.model.dao.InstrumentBrandsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InstrumentBrandsRepository(private val instrumentBrandsDao: InstrumentBrandsDAO) {
    var allInstrumentBrands: Flow<List<InstrumentBrands>> = instrumentBrandsDao.getInstrumentBrandsOrderedByName()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addInstrumentBrand(newInstrumentBrand: InstrumentBrands){
        coroutineScope.launch(Dispatchers.IO) {
            instrumentBrandsDao.insert(newInstrumentBrand)
        }
    }

    fun deleteInstrumentBrand(instrumentBrand: InstrumentBrands){
        coroutineScope.launch(Dispatchers.IO) {
            instrumentBrandsDao.delete(instrumentBrand)
        }
    }

    fun updateInstrumentBrand(instrumentBrand: InstrumentBrands){
        coroutineScope.launch(Dispatchers.IO) {
            instrumentBrandsDao.update(instrumentBrand)
        }
    }

    suspend fun getInstrumentBrandByID(id: Int): InstrumentBrands? {
        return instrumentBrandsDao.getInstrumentBrandByID(id)
    }
}