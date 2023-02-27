package com.example.officina20.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.officina20.database.OfficinaDatabase
import com.example.officina20.database.entity.Cliente
import com.example.officina20.database.entity.Intervento
import com.example.officina20.repository.InterventoRepository
import com.example.officina20.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InterventoViewModel(application: Application):AndroidViewModel(application) {
    private val repository: InterventoRepository

    init {
        val interventoDao = OfficinaDatabase.getDatabase(application).interventoDao()
        repository = InterventoRepository(interventoDao)
    }

    fun addIntervention(intervento: Intervento){
        //lancia l'applicazione in un thread in background, che viene eseguito in un thread
        //input/ouput adatto ad operazioni di lettura e scrittura o comunicazioni con un server
        //garantisce che l'applicazione rimanga reattiva
        viewModelScope.launch(Dispatchers.Default) {
            repository.addIntervention(intervento)
        }
    }

}