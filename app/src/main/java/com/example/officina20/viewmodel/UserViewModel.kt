package com.example.officina20.viewmodel

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.officina20.database.OfficinaDatabase
import com.example.officina20.database.entity.Cliente
import com.example.officina20.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Cliente>>
    private val repository: UserRepository

    init {
        val userDao = OfficinaDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(cliente: Cliente){
        //lancia l'applicazione in un thread in background, che viene eseguito in un thread
        //input/ouput adatto ad operazioni di lettura e scrittura o comunicazioni con un server
        //garantisce che l'applicazione rimanga reattiva
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(cliente)
        }
    }
    fun onRecyclerViewClick(position: Int) = position;
}