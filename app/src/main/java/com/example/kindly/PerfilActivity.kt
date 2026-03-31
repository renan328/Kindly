package com.example.kindly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflando com ViewBinding
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar os botões dinamicamente
        configurarBotoes()
    }

    private fun configurarBotoes() {
        // 1. Botão Transferir (já é o Pix, mas vamos garantir)
        // O id do include é includeTransferir, o id dentro dele é tvMenuText
        binding.includeTransferir.tvMenuText.text = "Transferir"
        binding.includeTransferir.ivMenuIcon.setImageResource(R.drawable.ic_transfer)

        // 2. Botão Conversão Dinheiro
        binding.includeConversao.tvMenuText.text = "Conversão Dinheiro"
        binding.includeConversao.ivMenuIcon.setImageResource(R.drawable.ic_money_globe)

        // 3. Botão Minhas Doações
        binding.includeDoacoes.tvMenuText.text = "Minhas Doações"
        binding.includeDoacoes.ivMenuIcon.setImageResource(R.drawable.ic_doacoes)

        // 4. Botão Precisa de Ajuda?
        binding.includeAjuda.tvMenuText.text = "Precisa de Ajuda?"
        binding.includeAjuda.ivMenuIcon.setImageResource(R.drawable.ic_help_question)

        // 5. Botão Informações Importantes
        binding.includeInfo.tvMenuText.text = "Informações Importantes"
        binding.includeInfo.ivMenuIcon.setImageResource(R.drawable.ic_info_talk)
    }
}