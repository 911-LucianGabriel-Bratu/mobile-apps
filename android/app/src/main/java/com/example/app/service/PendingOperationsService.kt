package com.example.app.service

import com.example.app.model.PendingOperations
import com.example.app.repository.PendingOperationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

class PendingOperationsService(private val repository: PendingOperationsRepository) {
    val allPendingOperations: Flow<List<PendingOperations>> = repository.allPendingOperations

    fun addPendingOperation(newPendingOperation: PendingOperations){
        repository.addPendingOperation(newPendingOperation)
    }

    fun getNextID(): Int {
        return repository.getNextID()
    }

    fun deletePendingOperation(pendingOperation: PendingOperations){
        return repository.deletePendingOperation(pendingOperation)
    }

    fun updatePendingOperation(pendingOperation: PendingOperations){
        return repository.updatePendingOperation(pendingOperation)
    }

    fun getFirstPendingOperation(){
        return repository.getFirstPendingOperation()
    }

    suspend fun getAllPendingOperations(): List<PendingOperations>{
        return repository.allPendingOperations.first()
    }

    suspend fun iterateAllPendingOperations(action: (List<PendingOperations>) -> Unit) {
        allPendingOperations.collect { pendingOperationsList ->
            action(pendingOperationsList)
        }
    }

    suspend fun getPendingOperationByID(id: Int): PendingOperations? {
        return repository.getPendingOperationByID(id)
    }
}