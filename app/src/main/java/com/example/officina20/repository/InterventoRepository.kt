package com.example.officina20.repository

import com.example.officina20.database.dao.DaoAuto
import com.example.officina20.database.dao.DaoInterventi
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Intervento

class InterventoRepository(private val interventoDao: DaoInterventi) {

    suspend fun addIntervention(intervento: Intervento) {
        interventoDao.addIntervention(intervento)
    }
}