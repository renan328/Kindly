package com.example.kindly

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityTransferirBinding

class TransferirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferirBinding
    private var ongId: Int = -1
    private var nomeOng: String = "ONG Desconhecida"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ongId = intent.getIntExtra("ONG_ID", -1)

        val ong = OngRepository.obterPorId(ongId)
        if (ong != null) {
            nomeOng = ong.nome
        }

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
            val valorString = binding.etValorDoacao.text.toString()
            val chavePix = binding.etChavePix.text.toString()

            if (valorString.isEmpty() || chavePix.isEmpty()) {
                Toast.makeText(this, "Gere a chave Pix antes de pagar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("KindlyPrefs", MODE_PRIVATE)
            val usuarioIdLogado = sharedPreferences.getInt("USUARIO_ID_LOGADO", -1)

            val helper = UserHelper(this)
            val db = helper.writableDatabase

            val valores = ContentValues().apply {
                put("usuario_id", usuarioIdLogado)
                put("ong_id", ongId)
                put("nome_ong", nomeOng)
                put("valor", valorString.toDouble())
                put("chave_pix", chavePix)
            }

            val resultado = db.insert("tbl_doacao", null, valores)
            db.close()

            if (resultado != -1L) {
                val intent = Intent(this, DoacaoFinalizadaActivity::class.java)

                intent.putExtra("DOACAO_ID", resultado)
                intent.putExtra("NOME_ONG", nomeOng)
                intent.putExtra("VALOR_DOACAO", valorString)

                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Erro ao processar banco.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}