package com.example.kindly

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityEditarPerfilBinding

class EditarPerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPerfilBinding
    private var usuarioId: Int = -1
    private var senhaCorreta: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carregarDadosUsuario()

        binding.btnBack.setOnClickListener { finish() }

        binding.btnSalvarAlteracoes.setOnClickListener {
            salvarAlteracoes()
        }
    }

    private fun carregarDadosUsuario() {
        var helper: UserHelper? = null
        try {
            helper = UserHelper(applicationContext)
            val db = helper.readableDatabase

            val cursor = db.rawQuery("SELECT id, nome, email, senha FROM tbl_usuario ORDER BY id DESC LIMIT 1", null)

            if (cursor.moveToFirst()) {
                usuarioId = cursor.getInt(0)
                binding.etEditarNome.setText(cursor.getString(1))
                binding.etEditarEmail.setText(cursor.getString(2))
                senhaCorreta = cursor.getString(3)
            }
            cursor.close()
            db.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Erro ao carregar dados", Toast.LENGTH_SHORT).show()
        } finally {
            helper?.close()
        }
    }

    private fun salvarAlteracoes() {
        val novoNome = binding.etEditarNome.text.toString().trim()
        val novoEmail = binding.etEditarEmail.text.toString().trim()
        val senhaDigitada = binding.etConfirmarSenha.text.toString().trim()

        if (novoNome.isEmpty()) {
            binding.etEditarNome.error = "O nome é obrigatório"
            return
        }
        if (novoEmail.isEmpty()) {
            binding.etEditarEmail.error = "O e-mail é obrigatório"
            return
        }
        if (senhaDigitada.isEmpty()) {
            binding.etConfirmarSenha.error = "A senha de confirmação é obrigatória"
            return
        }

        if (senhaDigitada != senhaCorreta) {
            binding.etConfirmarSenha.error = "Senha incorreta. Não foi possível salvar."
            Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
            return
        }

        var helper: UserHelper? = null
        try {
            helper = UserHelper(applicationContext)
            val db = helper.writableDatabase

            val valores = ContentValues().apply {
                put("nome", novoNome)
                put("email", novoEmail)
            }

            val linhasAfetadas = db.update("tbl_usuario", valores, "id = ?", arrayOf(usuarioId.toString()))
            db.close()

            if (linhasAfetadas > 0) {
                Toast.makeText(this, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao atualizar perfil", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Erro no banco de dados", Toast.LENGTH_SHORT).show()
        } finally {
            helper?.close()
        }
    }
}