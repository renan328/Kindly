package com.example.kindly

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityTransferirBinding

class AlterarDoacaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferirBinding
    private var doacaoId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doacaoId = intent.getLongExtra("DOACAO_ID", -1L)
        val valorAtual = intent.getStringExtra("VALOR_ATUAL") ?: ""

        binding.etValorDoacao.setText(valorAtual)
        binding.btnPagarPix.text = "Salvar Alteração"

        binding.btnGerarChave.setOnClickListener {
            val valor = binding.etValorDoacao.text.toString()
            if (valor.isNotEmpty()) {
                binding.etChavePix.setText("CHAVE-PIX-GERADA-KINDLY-$valor")
                Toast.makeText(this, "Chave gerada com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Digite um valor primeiro", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnPagarPix.setOnClickListener {
            val novoValorString = binding.etValorDoacao.text.toString()
            val novaChave = binding.etChavePix.text.toString()

            if (novoValorString.isEmpty() || novaChave.isEmpty()) {
                Toast.makeText(this, "Gere a chave Pix antes de salvar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val helper = UserHelper(this)
            val db = helper.writableDatabase

            val novosValores = ContentValues().apply {
                put("valor", novoValorString.toDouble())
                put("chave_pix", novaChave)
            }

            val linhasAfetadas = db.update(
                "tbl_doacao",
                novosValores,
                "id = ?",
                arrayOf(doacaoId.toString())
            )

            db.close()
            helper.close()

            if (linhasAfetadas > 0) {
                Toast.makeText(this, "Doação atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MinhasDoacoesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Erro ao atualizar no banco de dados. ID: $doacaoId", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}