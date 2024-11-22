package com.example.nicolericci_rm96154

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.nicolericci_rm96154.adapter.DicasAdapter
import com.example.nicolericci_rm96154.data.DicaModel
import com.example.nicolericci_rm96154.viewModel.DicasViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val viewModel: DicasViewModel by viewModels()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dicasAdapter = DicasAdapter { dica ->
            showDicaDialog(dica)
        }
        recyclerView.adapter = dicasAdapter

        val button = findViewById<Button>(R.id.button)
        val edtTitle = findViewById<EditText>(R.id.edtTitle)
        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)

        val searchView = findViewById<SearchView>(R.id.searchView)

        val btnIntegrantes = findViewById<Button>(R.id.btnIntegrantes)
        btnIntegrantes.setOnClickListener {
            showIntegrantesDialog()
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                dicasAdapter.filter(newText ?: "")
                return true
            }
        })

        button.setOnClickListener {
            if (edtTitle.text.isEmpty() || edtDescricao.text.isEmpty()) {
                edtTitle.error = "Preencha um valor"
                edtDescricao.error = "Preencha um valor"
                return@setOnClickListener
            }
            viewModel.addItem(edtTitle.text.toString(), edtDescricao.text.toString())
        }

        viewModel.dicasLiveData.observe(this) {
                items -> dicasAdapter.updateDicas(items.toMutableList())
        }

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

    }

    private fun showDicaDialog(dica: DicaModel) {
        AlertDialog.Builder(this)
            .setTitle(dica.titulo)
            .setMessage(dica.descricao)
            .setPositiveButton("Fechar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showIntegrantesDialog() {
        val integrantes = """
        1. Nicole Ricci - RM96154
        2. Yuri Gabriel- RM96069
    """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Integrantes do Projeto")
            .setMessage(integrantes)
            .setPositiveButton("Fechar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}