package com.example.officina20.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Cliente

@Dao
interface DaoAuto {

    @Query("SELECT * FROM auto WHERE cliente_id = :c LIMIT 1")
    suspend fun getAutoByClientId(c: Int?): Auto

    @Insert
    suspend fun addAuto(auto:Auto)

    @Query("SELECT * FROM auto")
    fun getAllAuto(): LiveData<List<Auto>>


}