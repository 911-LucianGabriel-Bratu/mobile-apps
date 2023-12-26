package com.example.app.service

import com.example.app.model.PendingOperations
import com.example.app.repository.PendingOperationsRepository
import kotlinx.coroutines.flow.Flow

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

    suspend fun getPendingOperationByID(id: Int): PendingOperations? {
        return repository.getPendingOperationByID(id)
    }
}