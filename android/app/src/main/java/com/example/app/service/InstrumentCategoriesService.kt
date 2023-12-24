package com.example.app.service

import com.example.app.model.InstrumentCategories
import com.example.app.repository.InstrumentCategoriesRepository
import kotlinx.coroutines.flow.Flow

class InstrumentCategoriesService(private val repository: InstrumentCategoriesRepository) {

    val allInstrumentCategories: Flow<List<InstrumentCategories>> = repository.allInstrumentCategories

    fun addInstrumentCategory(newInstrumentCategory: InstrumentCategories) {
        repository.addInstrumentCategory(newInstrumentCategory)
    }

    fun insertAll(instrumentCategoriesList: List<InstrumentCategories>){
        instrumentCategoriesList.forEach {
            repository.addInstrumentCategory(it)
        }
    }

    fun deleteInstrumentCategory(instrumentCategory: InstrumentCategories) {
        repository.deleteInstrumentCategory(instrumentCategory)
    }

    fun updateInstrumentCategory(instrumentCategory: InstrumentCategories) {
        repository.updateInstrumentCategory(instrumentCategory)
    }

    suspend fun getInstrumentCategoryByID(id: Int): InstrumentCategories? {
        return repository.getInstrumentCategoryByID(id)
    }
}