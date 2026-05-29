package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kindly.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        exibirOngs()
    }

    private fun exibirOngs() {
        val listaDeOngs = OngRepository.obterTodas()

        binding.rvOngsHome.layoutManager = LinearLayoutManager(this)

        binding.rvOngsHome.adapter = OngAdapter(listaDeOngs) { ongSelecionada ->
            val intent = Intent(this, DetalheOngActivity::class.java)
            intent.putExtra("ONG_ID", ongSelecionada.id)
            startActivity(intent)
        }
    }
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_search -> {
                    startActivity(Intent(this, ListaOngsActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}