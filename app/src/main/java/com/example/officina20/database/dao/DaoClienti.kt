package com.example.officina20.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.officina20.database.entity.Cliente

@Dao
interface DaoClienti {
    @Insert
    suspend fun addClient(cliente:Cliente)

    @Query("SELECT * FROM clienti")
    fun getAllClient(): LiveData<List<Cliente>>

}