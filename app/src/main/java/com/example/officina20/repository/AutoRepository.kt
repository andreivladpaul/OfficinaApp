package com.example.officina20.repository

import androidx.lifecycle.LiveData
import com.example.officina20.database.dao.DaoAuto
import com.example.officina20.database.entity.Auto

class AutoRepository(private val autoDao: DaoAuto) {
    val readAllData: LiveData<List<Auto>> = autoDao.getAllAuto()

    suspend fun addAuto(auto: Auto) {
        autoDao.addAuto(auto)
    }
}

