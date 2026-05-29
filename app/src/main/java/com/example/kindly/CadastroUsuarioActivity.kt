package com.example.kindly

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityCadastroUsuarioBinding
class CadastroUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastra.setOnClickListener {
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario() {
        val nome = binding.etNome.text.toString()
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()
        val confirmaSenha = binding.etConfirmaSenha.text.toString()

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (senha != confirmaSenha) {
            Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show()

            binding.etSenha.text?.clear()
            binding.etConfirmaSenha.text?.clear()
            return
        }

        val valores = android.content.ContentValues()
        valores.put("nome", nome)
        valores.put("email", email)
        valores.put("senha", senha)

        var helper: UserHelper? = null

        try {
            helper = UserHelper(applicationContext)

            val db = helper.writableDatabase

            val id = db.insert("tbl_usuario", "", valores)

            if (id == -1L) {
                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                finish()
            }

            db.close()

        } catch (e: Exception) {
            Toast.makeText(this, "Erro no banco", Toast.LENGTH_SHORT).show()
        } finally {
            helper?.close()
        }
    }
}