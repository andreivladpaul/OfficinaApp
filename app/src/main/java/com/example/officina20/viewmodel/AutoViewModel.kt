package com.example.officina20.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.officina20.database.OfficinaDatabase
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Cliente
import com.example.officina20.repository.AutoRepository
import com.example.officina20.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AutoViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Auto>>
    private val repository: AutoRepository

    init {
        val autoDao = OfficinaDatabase.getDatabase(application).autoDao()
        repository = AutoRepository(autoDao)
        readAllData = repository.readAllData
    }

    fun addAuto(auto: Auto){
        //lancia l'applicazione in un thread in background, che viene eseguito in un thread
        //input/ouput adatto ad operazioni di lettura e scrittura o comunicazioni con un server
        //garantisce che l'applicazione rimanga reattiva
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAuto(auto)
        }
    }
    fun onRecyclerViewClick(position: Int) = position;
}
