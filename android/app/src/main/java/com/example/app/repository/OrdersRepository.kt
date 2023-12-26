package com.example.app.repository

import com.example.app.model.Orders
import com.example.app.model.dao.OrdersDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OrdersRepository(private val ordersDao: OrdersDAO) {
    val allOrders: Flow<List<Orders>> = ordersDao.getAllOrders()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addOrder(newOrder: Orders){
        coroutineScope.launch(Dispatchers.IO) {
            ordersDao.insert(newOrder)
        }
    }

    fun deleteOrder(order: Orders){
        coroutineScope.launch(Dispatchers.IO) {
            ordersDao.delete(order)
        }
    }

    fun updateOrder(order: Orders){
        coroutineScope.launch(Dispatchers.IO) {
            ordersDao.update(order)
        }
    }

    fun getNextID(): Int {
        val allOrderIds = ordersDao.getAllOrderIds()
        val maxId = allOrderIds.maxOrNull() ?: 0
        return maxId + 1
    }

    suspend fun getOrderByID(id: Int): Orders? {
        return ordersDao.getOrderById(id)
    }

    fun getOrdersByUser(userID: Int): Flow<List<Orders>> {
        return ordersDao.getOrdersByUser(userID)
    }

    fun getOrdersByInstrument(instrumentID: Int): Flow<List<Orders>> {
        return ordersDao.getOrdersByInstrument(instrumentID)
    }
}