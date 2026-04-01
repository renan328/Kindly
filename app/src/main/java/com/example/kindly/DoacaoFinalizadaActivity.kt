package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityDoacaoFinalizadaBinding

class DoacaoFinalizadaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoacaoFinalizadaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDoacaoFinalizadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnVerDoacoes.setOnClickListener {
            val intent = Intent(this, MinhasDoacoesActivity::class.java)
            startActivity(intent)
        }

        binding.btnCancelarDoacao.setOnClickListener {
            finish()
        }
    }
}