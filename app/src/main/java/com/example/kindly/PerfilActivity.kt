package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarBotoes()
        configurarCliques()
    }

    private fun configurarBotoes() {
        binding.includeDoacoes.tvMenuText.text = "Minhas Doações"
        binding.includeDoacoes.ivMenuIcon.setImageResource(R.drawable.ic_doacoes)

        binding.includeAjuda.tvMenuText.text = "Precisa de Ajuda?"
        binding.includeAjuda.ivMenuIcon.setImageResource(R.drawable.ic_help_question)
    }

    private fun configurarCliques() {
        binding.btnBack.setOnClickListener { finish() }

        binding.includeDoacoes.root.setOnClickListener {
            startActivity(Intent(this, MinhasDoacoesActivity::class.java))
        }

        binding.btnEditarPerfil.setOnClickListener {
            val intent = Intent(this, CadastroUsuarioActivity::class.java)
            startActivity(intent)
        }

        binding.btnExcluirConta.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}