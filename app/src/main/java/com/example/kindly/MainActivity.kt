package com.example.kindly // Verifique se este é o SEU pacote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityMainBinding // O erro 'main' morre aqui

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Se o XML estiver corrigido, o inflate vai funcionar
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Teste de acesso
        // Dentro do onCreate, após o setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            // 1. Criamos a Intent: (De onde estamos, Para onde vamos)
            val intent = Intent(this, HomeActivity::class.java)

            // 2. Iniciamos a nova Activity
            startActivity(intent)

            // 3. (Opcional) Se você não quer que o usuário volte para o login ao clicar em "Voltar"
            // finish()
        }
        binding.tvNoAccount.setOnClickListener {
            val intent = Intent(this, CadastroUsuarioActivity::class.java)

            // 2. Iniciamos a nova Activity
            startActivity(intent)
        }
    }
}