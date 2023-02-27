package com.example.officina20.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Intervento

@Dao
interface DaoInterventi {

    @Insert
    suspend fun addIntervention(intervento: Intervento)

    @Query("SELECT * FROM interventi WHERE id_auto = :i LIMIT 1")
    suspend fun getInterventionByPlate(i: Int): Intervento
}