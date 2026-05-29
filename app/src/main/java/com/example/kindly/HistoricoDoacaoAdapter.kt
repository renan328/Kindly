package com.example.kindly

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class HistoricoDoacaoAdapter(
    private val listaTextos: List<String>,
    private val listaCores: List<String>,
    private val listaValoresPuros: List<Double>,
    private val listaNomesPuros: List<String>,
    private val listaIdsPuros: List<Long>,
    private val onItemClick: (idDoacao: Long, nomeOng: String, valor: String) -> Unit
) : RecyclerView.Adapter<HistoricoDoacaoAdapter.HistoricoViewHolder>() {

    class HistoricoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tvTextoHistorico)
        val cardAvatar: CardView = itemView.findViewById(R.id.cardAvatarHistorico)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_historico_doacao, parent, false
        )
        return HistoricoViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
        holder.textView.text = listaTextos[position]

        try {
            holder.cardAvatar.setCardBackgroundColor(Color.parseColor(listaCores[position]))
        } catch (e: Exception) {
            holder.cardAvatar.setCardBackgroundColor(Color.parseColor("#5770A4"))
        }

        holder.itemView.setOnClickListener {
            onItemClick(
                listaIdsPuros[position],
                listaNomesPuros[position],
                listaValoresPuros[position].toString()
            )
        }
    }

    override fun getItemCount() = listaTextos.size
}