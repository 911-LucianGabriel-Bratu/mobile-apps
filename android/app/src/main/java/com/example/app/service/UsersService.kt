package com.example.app.service

import com.example.app.model.Users
import com.example.app.repository.UsersRepository
import kotlinx.coroutines.flow.Flow

class UsersService(private val repository: UsersRepository) {

    val allUsers: Flow<List<Users>> = repository.allUsers

    fun addUser(newUser: Users) {
        repository.addUser(newUser)
    }

    fun deleteUser(user: Users) {
        repository.deleteUser(user)
    }

    fun updateUser(user: Users) {
        repository.updateUser(user)
    }

    suspend fun getUserByID(id: Int): Users? {
        return repository.getUserByID(id)
    }
}