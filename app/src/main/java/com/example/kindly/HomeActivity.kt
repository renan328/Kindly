package com.example.kindly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kindly.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inflar o layout
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Configurar o Menu Inferior (Bottom Navigation)
        setupBottomNavigation()

        // 3. Simular o carregamento de dados (Opcional por enquanto)
        renderizarListaDeOngs()
        // Dentro do onCreate da HomeActivity

    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Já estamos na Home
                    true
                }
                R.id.nav_search -> {
                    Toast.makeText(this, "Abrir Busca", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    // 2. Iniciamos a nova Activity
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun renderizarListaDeOngs() {
        // Por enquanto, como você está montando o layout,
        // este método pode ficar vazio ou ser usado para
        // colocar lógica de clique nos itens da lista se você usar RecyclerView.
    }
}