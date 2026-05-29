package com.example.kindly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carregarPerfil()
        configurarBotoes()
        configurarCliques()
    }

    override fun onResume() {
        super.onResume()
        carregarPerfil()
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
            val intent = Intent(this, EditarPerfilActivity::class.java)
            startActivity(intent)
        }

        binding.btnExcluirConta.setOnClickListener {
            exibirModalConfirmacaoExclusao()
        }
    }

    private fun carregarPerfil() {
        var helper: UserHelper? = null
        try {
            helper = UserHelper(applicationContext)
            val db = helper.readableDatabase

            val sharedPreferences = getSharedPreferences("KindlyPrefs", MODE_PRIVATE)
            val usuarioIdLogado = sharedPreferences.getInt("USUARIO_ID_LOGADO", -1)

            if (usuarioIdLogado == -1) {
                binding.tvProfileName.text = "Usuário Desconectado"
                return
            }

            val cursor = db.rawQuery(
                "SELECT nome FROM tbl_usuario WHERE id = ?",
                arrayOf(usuarioIdLogado.toString())
            )

            if (cursor.moveToFirst()) {
                binding.tvProfileName.text = cursor.getString(0)
            } else {
                binding.tvProfileName.text = "Usuário não encontrado"
            }

            cursor.close()
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
            binding.tvProfileName.text = "Erro ao carregar"
        } finally {
            helper?.close()
        }
    }

    private fun exibirModalConfirmacaoExclusao() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Excluir Conta")
        builder.setMessage("Tem certeza de que deseja excluir sua conta do Kindly? Esta ação não pode ser desfeita.")

        builder.setPositiveButton("Sim, excluir") { dialog, which ->
            deletarUsuarioDoBanco()
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun deletarUsuarioDoBanco() {
        var helper: UserHelper? = null
        try {
            helper = UserHelper(applicationContext)
            val db = helper.writableDatabase

            val cursor = db.rawQuery("SELECT id FROM tbl_usuario ORDER BY id DESC LIMIT 1", null)

            if (cursor.moveToFirst()) {
                val idUsuario = cursor.getInt(0)

                val linhasDeletadas = db.delete("tbl_usuario", "id = ?", arrayOf(idUsuario.toString()))

                if (linhasDeletadas > 0) {
                    Toast.makeText(this, "Conta excluída com sucesso", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            cursor.close()
            db.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao tentar deletar conta", Toast.LENGTH_SHORT).show()
        } finally {
            helper?.close()
        }
    }
}