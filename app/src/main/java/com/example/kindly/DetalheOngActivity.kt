package com.example.kindly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityDetalheOngBinding
class DetalheOngActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheOngBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalheOngBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Receber os dados passados pela Intent
        val nomeRecebido = intent.getStringExtra("NOME_ONG") ?: "Nome da ONG"
        val categoriaRecebida = intent.getStringExtra("CATEGORIA_ONG") ?: "Categoria"

        // 4. Aplicar os textos usando o Binding
        // Os IDs devem ser os mesmos definidos no seu XML de detalhes
        binding.tvNomeOngDetalhe.text = nomeRecebido
        binding.tvCategoriaOngDetalhe.text = categoriaRecebida

        // 5. Configurar o botão de voltar
        binding.btnBack.setOnClickListener {
            finish() // Fecha esta tela e volta para a listagem
        }

        // Exemplo: Se quiser configurar o botão de doação que está no layout
        // Dentro do onCreate, após as outras configurações
        binding.btnRealizarDoacao.setOnClickListener {
            // 1. Criamos a Intent para a nova tela
            val intent = Intent(this, TransferirActivity::class.java)

            // 2. Opcional: Passar o nome da ONG para a tela de transferência
            val nomeOng = binding.tvNomeOngDetalhe.text.toString()
            intent.putExtra("NOME_ONG", nomeOng)

            // 3. Iniciar a Activity
            startActivity(intent)
        }
    }
}