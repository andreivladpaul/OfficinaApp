package com.example.officina20.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.officina20.database.dao.DaoAuto
import com.example.officina20.database.dao.DaoClienti
import com.example.officina20.database.dao.DaoInterventi
import com.example.officina20.database.entity.Auto
import com.example.officina20.database.entity.Cliente
import com.example.officina20.database.entity.Intervento

@Database(entities = [Cliente::class,Auto::class,Intervento::class], version = 2, exportSchema = false )
abstract class OfficinaDatabase: RoomDatabase() {

    abstract fun userDao(): DaoClienti
    abstract fun autoDao(): DaoAuto
    abstract fun interventoDao(): DaoInterventi

    companion object{
        @Volatile
        private var INSTANCE: OfficinaDatabase? = null

        fun getDatabase(context: Context): OfficinaDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OfficinaDatabase::class.java,
                    name = "database_officina"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}