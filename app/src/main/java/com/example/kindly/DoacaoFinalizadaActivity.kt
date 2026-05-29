package com.example.kindly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityDoacaoFinalizadaBinding

class DoacaoFinalizadaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoacaoFinalizadaBinding
    private var doacaoId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDoacaoFinalizadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doacaoId = intent.getLongExtra("DOACAO_ID", -1L)
        val nomeOng = intent.getStringExtra("NOME_ONG") ?: "ONG Beneficiada"
        val valorDoacao = intent.getStringExtra("VALOR_DOACAO") ?: "0.00"

        binding.tvNomeOngFinal.text = nomeOng
        binding.tvLabelValor.text = String.format("Valor    R$ %,.2f", valorDoacao.toDoubleOrNull() ?: 0.0)

        binding.btnBack.setOnClickListener { finish() }

        binding.btnVerDoacoes.setOnClickListener {
            startActivity(Intent(this, MinhasDoacoesActivity::class.java))
            finish()
        }

        binding.btnAlterarValor.setOnClickListener {
            val intent = Intent(this, AlterarDoacaoActivity::class.java)
            intent.putExtra("DOACAO_ID", doacaoId) // Passa o ID Long adiante
            intent.putExtra("VALOR_ATUAL", valorDoacao)
            startActivity(intent)
            finish()
        }

        binding.btnCancelarDoacao.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Cancelar Doação")
            builder.setMessage("Tem certeza que deseja cancelar e excluir esta doação?")

            builder.setPositiveButton("Sim, cancelar") { dialog, _ ->
                if (doacaoId != -1L) {
                    val helper = UserHelper(this)
                    val db = helper.writableDatabase

                    val linhasApagadas = db.delete("tbl_doacao", "id = ?", arrayOf(doacaoId.toString()))
                    db.close()
                    helper.close()

                    if (linhasApagadas > 0) {
                        Toast.makeText(this, "Doação excluída com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Erro: Registro não encontrado no banco. ID: $doacaoId", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Erro: ID inválido no cancelamento (-1).", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }

            builder.setNegativeButton("Não") { dialog, _ -> dialog.dismiss() }
            builder.create().show()
        }
    }
}