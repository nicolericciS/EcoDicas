package com.example.nicolericci_rm96154.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nicolericci_rm96154.data.DicaModel

@Dao
interface DicaDao {

    @Query("SELECT * FROM DicaModel")
    fun getAll(): LiveData<List<DicaModel>>

    @Insert
    fun insert(dica: DicaModel)

    @Delete
    fun delete(dica: DicaModel)
}