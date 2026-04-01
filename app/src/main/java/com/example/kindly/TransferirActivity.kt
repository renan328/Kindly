package com.example.kindly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityTransferirBinding

class TransferirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGerarChave.setOnClickListener {
            val valor = binding.etValorDoacao.text.toString()
            if (valor.isNotEmpty()) {
                binding.etChavePix.setText("CHAVE-PIX-GERADA-KINDLY-"+valor)
                Toast.makeText(this, "Chave gerada com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Digite um valor primeiro", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnPagarPix.setOnClickListener {
            val intent = Intent(this, DoacaoFinalizadaActivity::class.java)
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}