package com.example.kindly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
                R.id.nav_home -> {
                    finish()
                    true
                }
                else -> false
            }
        }

        carregarHistoricoDoacoes()
    }

    private fun carregarHistoricoDoacoes() {
        val textosFormatados = ArrayList<String>()
        val coresAvatares = ArrayList<String>()
        val valoresPuros = ArrayList<Double>()
        val nomesPuros = ArrayList<String>()
        val idsPuros = ArrayList<Long>()

        var helper: UserHelper? = null

        try {
            helper = UserHelper(this)
            val db = helper.readableDatabase

            val sharedPreferences = getSharedPreferences("KindlyPrefs", MODE_PRIVATE)
            val usuarioIdLogado = sharedPreferences.getInt("USUARIO_ID_LOGADO", -1)

            val cursor = db.rawQuery(
                "SELECT id, ong_id, nome_ong, valor FROM tbl_doacao WHERE usuario_id = ? ORDER BY id DESC",
                arrayOf(usuarioIdLogado.toString())
            )

            if (cursor.moveToFirst()) {
                do {
                    val doacaoId = cursor.getLong(0)
                    val ongId = cursor.getInt(1)
                    val nomeOng = cursor.getString(2)
                    val valor = cursor.getDouble(3)

                    // Alimenta as listas primitivas de exibição e transporte
                    val textoItem = String.format("%s — R$ %,.2f", nomeOng, valor)
                    textosFormatados.add(textoItem)
                    valoresPuros.add(valor)
                    nomesPuros.add(nomeOng)
                    idsPuros.add(doacaoId)

                    val ongMemoria = OngRepository.obterPorId(ongId)
                    coresAvatares.add(ongMemoria?.corBackgroundHex ?: "#5770A4")

                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao carregar histórico", Toast.LENGTH_SHORT).show()
        } finally {
            helper?.close()
        }

        binding.rvHistoricoDoacoes.layoutManager = LinearLayoutManager(this)

        binding.rvHistoricoDoacoes.adapter = HistoricoDoacaoAdapter(
            textosFormatados,
            coresAvatares,
            valoresPuros,
            nomesPuros,
            idsPuros
        ) { idSelecionado, nomeSelecionado, valorSelecionado ->

            val intent = Intent(this, DoacaoFinalizadaActivity::class.java)
            intent.putExtra("DOACAO_ID", idSelecionado)
            intent.putExtra("NOME_ONG", nomeSelecionado)
            intent.putExtra("VALOR_DOACAO", valorSelecionado)
            startActivity(intent)
        }
    }
}