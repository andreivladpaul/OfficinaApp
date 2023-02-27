package com.example.officina20.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "auto",
    foreignKeys = [ForeignKey(
        entity = Cliente::class,
        parentColumns = arrayOf("clienteId"),
        childColumns = arrayOf("cliente_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Auto(
    @PrimaryKey(autoGenerate = true)
    val idAuto:Int,
    val nTelaio: String,
    val marca:String,
    val modello:String,
    val targa:String,
    val cliente_id: Int
    ) {
}