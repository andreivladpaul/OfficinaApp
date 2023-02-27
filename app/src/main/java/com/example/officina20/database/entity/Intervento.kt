package com.example.officina20.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "interventi",
    foreignKeys = [ForeignKey(
        entity = Auto::class,
        parentColumns = arrayOf("idAuto"),
        childColumns = arrayOf("id_auto"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Intervento(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val descrizione:String,
    val durata:Float,
    @ColumnInfo(name = "data_arrivo")
    val dataArrivo: String,
    @ColumnInfo(name = "data_consegna")
    val dataConsegna:String,
    val id_auto:Int
) {
}