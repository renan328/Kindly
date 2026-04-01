package com.example.kindly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityMinhasDoacoesBinding

class MinhasDoacoesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinhasDoacoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMinhasDoacoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> { true }
                else -> false
            }
            true
        }
    }
}