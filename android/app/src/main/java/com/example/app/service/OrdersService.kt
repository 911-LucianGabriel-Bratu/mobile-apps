package com.example.app.service

import com.example.app.model.Orders
import com.example.app.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow

class OrdersService(private val repository: OrdersRepository) {

    val allOrders: Flow<List<Orders>> = repository.allOrders

    fun addOrder(newOrder: Orders) {
        repository.addOrder(newOrder)
    }

    fun insertAll(ordersList: List<Orders>){
        ordersList.forEach {
            repository.addOrder(it);
        }
    }

    fun getNextID(): Int {
        return repository.getNextID()
    }

    fun deleteOrder(order: Orders) {
        repository.deleteOrder(order)
    }

    fun updateOrder(order: Orders) {
        repository.updateOrder(order)
    }

    suspend fun getOrderByID(id: Int): Orders? {
        return repository.getOrderByID(id)
    }

    fun getOrdersByUser(userID: Int): Flow<List<Orders>> {
        return repository.getOrdersByUser(userID)
    }

    fun getOrdersByInstrument(instrumentID: Int): Flow<List<Orders>> {
        return repository.getOrdersByInstrument(instrumentID)
    }
}