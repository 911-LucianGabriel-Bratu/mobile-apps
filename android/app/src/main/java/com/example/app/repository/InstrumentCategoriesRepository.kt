package com.example.app.repository

import com.example.app.model.InstrumentCategories
import com.example.app.model.dao.InstrumentCategoriesDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InstrumentCategoriesRepository(private val instrumentCategoriesDao: InstrumentCategoriesDAO) {
    val allInstrumentCategories: Flow<List<InstrumentCategories>> = instrumentCategoriesDao.getInstrumentCategories()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addInstrumentCategory(newInstrumentCategory: InstrumentCategories){
        coroutineScope.launch(Dispatchers.IO) {
            instrumentCategoriesDao.insert(newInstrumentCategory)
        }
    }

    fun deleteInstrumentCategory(instrumentCategory: InstrumentCategories){
        coroutineScope.launch(Dispatchers.IO) {
            instrumentCategoriesDao.delete(instrumentCategory)
        }
    }

    fun updateInstrumentCategory(instrumentCategory: InstrumentCategories){
        coroutineScope.launch(Dispatchers.IO) {
            instrumentCategoriesDao.update(instrumentCategory)
        }
    }

    suspend fun getInstrumentCategoryByID(id: Int): InstrumentCategories? {
        return instrumentCategoriesDao.getInstrumentCategoryByID(id)
    }
}