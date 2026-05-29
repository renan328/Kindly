package com.example.kindly

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kindly.databinding.ActivityMainBinding
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val emailDigitado = binding.etEmail.text.toString().trim().lowercase()
            val senhaDigitada = binding.etPassword.text.toString().trim()

            val helper = UserHelper(this)
            val db = helper.readableDatabase

            val cursor = db.rawQuery(
                "SELECT id FROM tbl_usuario WHERE LOWER(email) = ? AND senha = ?",
                arrayOf(emailDigitado, senhaDigitada)
            )

            if (cursor.moveToFirst()) {
                val usuarioId = cursor.getInt(0)

                val sharedPreferences = getSharedPreferences("KindlyPrefs", MODE_PRIVATE)
                sharedPreferences.edit { putInt("USUARIO_ID_LOGADO", usuarioId) }

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "E-mail ou senha incorretos!", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
            db.close()
        }
        binding.tvNoAccount.setOnClickListener {
            val intent = Intent(this, CadastroUsuarioActivity::class.java)

            startActivity(intent)
        }
    }
}