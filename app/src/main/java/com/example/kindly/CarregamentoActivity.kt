package com.example.kindly // Verifique se é o SEU pacote correto

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityCarregamentoBinding

class CarregamentoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarregamentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflando com ViewBinding
        binding = ActivityCarregamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simula o carregamento e vai para a MainActivity (Login)
        Handler(Looper.getMainLooper()).postDelayed({
            // Garanta que você já corrigiu a MainActivity como combinamos nas mensagens anteriores
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Impede que o usuário volte para a splash
        }, 2500) // 2.5 segundos
    }
}