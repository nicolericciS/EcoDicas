package com.example.nicolericci_rm96154.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nicolericci_rm96154.data.DicaModel


@Database(entities = [DicaModel::class], version = 1)

abstract class DicaDatabase : RoomDatabase() {

    abstract fun dicaDao(): DicaDao
}