package com.example.kindly // Certifique-se de que este é o nome real do seu pacote

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityCadastroUsuarioBinding // O nome deve bater com o seu arquivo XML

class CadastroUsuarioActivity : AppCompatActivity() {

    // 1. Declaramos a variável do binding
    private lateinit var binding: ActivityCadastroUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inflamos o layout
        binding = ActivityCadastroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Configuramos o clique do botão CADASTRA
        binding.btnCadastra.setOnClickListener {
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario() {
        // Pegamos os textos dos campos usando o binding
        val nome = binding.etNome.text.toString()
        val senha = binding.etSenha.text.toString()
        val confirmaSenha = binding.etConfirmaSenha.text.toString()

        // Validação básica de Campos Vazios
        if (nome.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Validação: As senhas são iguais?
        if (senha == confirmaSenha) {
            // Se as senhas forem iguais, simulamos o sucesso
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()

            // Fecha esta tela e volta para o Login
            finish()
        } else {
            // Se forem diferentes, avisamos o usuário
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show()

            // Opcional: Limpar os campos de senha para o usuário tentar de novo
            binding.etSenha.text?.clear()
            binding.etConfirmaSenha.text?.clear()
        }
    }
}