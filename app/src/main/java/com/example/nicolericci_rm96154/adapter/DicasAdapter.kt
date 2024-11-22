package com.example.nicolericci_rm96154.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nicolericci_rm96154.R
import com.example.nicolericci_rm96154.data.DicaModel

class DicasAdapter (private val onItemClicked: (DicaModel) -> Unit): RecyclerView.Adapter<DicasAdapter.DicaViewHolder>() {

    private var dicas = mutableListOf<DicaModel>()
    private var dicasFiltradas = mutableListOf<DicaModel>()

    init {
        dicasFiltradas = dicas
    }

    inner class DicaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTitulo = view.findViewById<TextView>(R.id.tvTitulo)
        val tvDescricao = view.findViewById<TextView>(R.id.tvDescricao)

        fun bind(dica: DicaModel) {
            tvTitulo.text = dica.titulo
            tvDescricao.text = dica.descricao
            itemView.setOnClickListener {
                onItemClicked(dica)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dica_layout, parent, false)
        return DicaViewHolder(view)
    }

    override fun getItemCount(): Int = dicasFiltradas.size

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicasFiltradas[position]
        holder.bind(dica)
    }

    fun addDica(novaDica: DicaModel) {
        dicas.add(novaDica)
        notifyItemInserted(dicas.size - 1)
    }

    fun updateDicas(newItems: MutableList<DicaModel>) {
        dicas = newItems
        dicasFiltradas = newItems
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        dicasFiltradas = if (query.isEmpty()) {
            dicas
        } else {
            dicas.filter {
                it.titulo.contains(query, ignoreCase = true) ||
                        it.descricao.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


}