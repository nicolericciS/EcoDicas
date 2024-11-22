package com.example.nicolericci_rm96154.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.nicolericci_rm96154.data.DicaModel
import com.example.nicolericci_rm96154.database.DicaDao
import com.example.nicolericci_rm96154.database.DicaDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DicasViewModel(application: Application) : AndroidViewModel(application) {

    private var items = mutableListOf<DicaModel>()
    private var dicaDao: DicaDao
    val dicasLiveData: LiveData<List<DicaModel>>


    init {
        val database = Room.databaseBuilder(
            getApplication(),
            DicaDatabase::class.java,
            "dicas_database"
        ).build()

        dicaDao = database.dicaDao()
        dicasLiveData = dicaDao.getAll()
    }


    fun addItem(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = DicaModel(titulo = title, descricao = description)
            dicaDao.insert(newItem)
        }
    }
}