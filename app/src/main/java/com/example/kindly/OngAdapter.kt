package com.example.kindly

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kindly.databinding.ItemOngFixoBinding
import androidx.core.graphics.toColorInt

class OngAdapter(
    private val ongs: List<Ong>,
    private val onItemClick: (Ong) -> Unit
) : RecyclerView.Adapter<OngAdapter.OngViewHolder>() {

    class OngViewHolder(val binding: ItemOngFixoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OngViewHolder {
        val binding = ItemOngFixoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OngViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OngViewHolder, position: Int) {
        val ong = ongs[position]

        holder.binding.tvNomeOng.text = ong.nome

        holder.binding.tvCausaOng.text = ong.descricao

        try {
            holder.binding.cardAvatarOng.setCardBackgroundColor(ong.corBackgroundHex.toColorInt())
        } catch (e: Exception) {
            holder.binding.cardAvatarOng.setCardBackgroundColor(Color.GRAY)
        }

        holder.itemView.setOnClickListener { onItemClick(ong) }
    }

    override fun getItemCount() = ongs.size
}