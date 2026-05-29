package com.example.kindly

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kindly.databinding.ItemListaOngBinding
import androidx.core.graphics.toColorInt

class ListaOngAdapter(
    private val ongs: List<Ong>,
    private val onItemClick: (Ong) -> Unit
) : RecyclerView.Adapter<ListaOngAdapter.ListaOngViewHolder>() {

    class ListaOngViewHolder(val binding: ItemListaOngBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaOngViewHolder {
        val binding = ItemListaOngBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListaOngViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListaOngViewHolder, position: Int) {
        val ong = ongs[position]

        holder.binding.tvNomeLista.text = ong.nome
        holder.binding.tvCausaLista.text = ong.descricao

        try {
            holder.binding.cardAvatarLista.setCardBackgroundColor(ong.corBackgroundHex.toColorInt())
        } catch (e: Exception) {
            holder.binding.cardAvatarLista.setCardBackgroundColor(Color.GRAY)
        }

        holder.itemView.setOnClickListener { onItemClick(ong) }
    }

    override fun getItemCount() = ongs.size
}