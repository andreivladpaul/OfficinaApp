package com.example.officina20.database.entity

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clienti"
)
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val clienteId:Int,
    val nome:String,
    val cognome:String,
    val email: String
) {
}