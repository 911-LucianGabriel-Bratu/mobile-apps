package com.example.app.repository

import com.example.app.model.Users
import com.example.app.model.dao.UsersDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UsersRepository(private val usersDao: UsersDAO) {
    var allUsers: Flow<List<Users>> = usersDao.getAllUsers()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addUser(newUser: Users){
        coroutineScope.launch(Dispatchers.IO) {
            usersDao.insert(newUser)
        }
    }

    fun deleteUser(user: Users){
        coroutineScope.launch(Dispatchers.IO) {
            usersDao.delete(user)
        }
    }

    fun updateUser(user: Users){
        coroutineScope.launch(Dispatchers.IO) {
            usersDao.update(user)
        }
    }

    suspend fun getUserByID(id: Int): Users? {
        return usersDao.getUserById(id)
    }
}