package com.example.kindly

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityDetalheOngBinding
import androidx.core.graphics.toColorInt

class DetalheOngActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheOngBinding
    private var ongId: Int = -1 // Armazena o ID da ONG atual nesta tela

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalheOngBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ongId = intent.getIntExtra("ONG_ID", -1)

        val ong = OngRepository.obterPorId(ongId)

        if (ong != null) {
            binding.tvNomeOngDetalhe.text = ong.nome
            binding.tvCategoriaOngDetalhe.text = ong.descricao

            try {
                binding.cardTopo.setCardBackgroundColor(ong.corBackgroundHex.toColorInt())
            } catch (e: Exception) {
                binding.cardTopo.setCardBackgroundColor("#5770A4".toColorInt())
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnRealizarDoacao.setOnClickListener {
            val intent = Intent(this, TransferirActivity::class.java)

            intent.putExtra("ONG_ID", ongId)

            startActivity(intent)
        }
    }
}