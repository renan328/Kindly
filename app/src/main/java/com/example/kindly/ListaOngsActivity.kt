package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kindly.databinding.ActivityListaOngsBinding

class ListaOngsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaOngsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaOngsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        configurarLista()
    }

    private fun configurarLista() {
        val todasOngs = OngRepository.obterTodas()

        binding.rvListaTodasOngs.layoutManager = LinearLayoutManager(this)

        binding.rvListaTodasOngs.adapter = ListaOngAdapter(todasOngs) { ongSelecionada ->
            val intent = Intent(this, DetalheOngActivity::class.java)

            intent.putExtra("ONG_ID", ongSelecionada.id)

            startActivity(intent)
        }
    }
}