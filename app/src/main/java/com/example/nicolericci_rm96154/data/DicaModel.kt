package com.example.nicolericci_rm96154.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DicaModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descricao: String
)