package com.example.officina20.repository

import androidx.lifecycle.LiveData
import com.example.officina20.database.dao.DaoClienti
import com.example.officina20.database.entity.Cliente

class UserRepository(private val clientDao: DaoClienti) {
    val readAllData: LiveData<List<Cliente>> = clientDao.getAllClient()

    suspend fun addUser(cliente: Cliente) {
        clientDao.addClient(cliente)
    }
}