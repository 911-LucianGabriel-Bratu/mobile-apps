package com.example.app.repository

import com.example.app.model.PendingOperations
import com.example.app.model.dao.PendingOperationsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PendingOperationsRepository(private val pendingOperationsDAO: PendingOperationsDAO) {
    val allPendingOperations: Flow<List<PendingOperations>> = pendingOperationsDAO.getAllPendingOperations()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addPendingOperation(newPendingOperation: PendingOperations){
        coroutineScope.launch(Dispatchers.IO){
            pendingOperationsDAO.insert(newPendingOperation)
        }
    }

    fun deletePendingOperation(pendingOperation: PendingOperations){
        coroutineScope.launch(Dispatchers.IO){
            pendingOperationsDAO.delete(pendingOperation)
        }
    }

    fun updatePendingOperation(pendingOperation: PendingOperations){
        coroutineScope.launch(Dispatchers.IO){
            pendingOperationsDAO.update(pendingOperation)
        }
    }

    fun getNextID(): Int {
        val allPendingOperationIDs = pendingOperationsDAO.getAllPendingOperationsIds()
        val maxId = allPendingOperationIDs.maxOrNull() ?: 0
        return maxId + 1
    }

    suspend fun getPendingOperationByID(id: Int): PendingOperations? {
        return pendingOperationsDAO.getPendingOperationById(id)
    }
}