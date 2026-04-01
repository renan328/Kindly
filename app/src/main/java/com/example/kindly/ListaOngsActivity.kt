package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityListaOngsBinding
import com.example.kindly.DetalheOngActivity

class ListaOngsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaOngsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaOngsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemOngFelizes.setOnClickListener {
            val intent = Intent(this, DetalheOngActivity::class.java)
            intent.putExtra("NOME_ONG", "Felizes Animais")
            intent.putExtra("CATEGORIA_ONG", "Animais")
            startActivity(intent)
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}