package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_search -> {
                    val intent = Intent(this, ListaOngsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}