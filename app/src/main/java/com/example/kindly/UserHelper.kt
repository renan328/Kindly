package com.example.kindly

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Cria a tabela de usuários
        db.execSQL(TABLE_CREATE_USUARIO)
        // Cria a tabela de doações
        db.execSQL(TABLE_CREATE_DOACAO)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (oldVersion < 2) {
            db.execSQL(TABLE_CREATE_DOACAO)
        }
    }

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "db_kindly"

        // TABELA USUÁRIO
        private const val TABELA_USUARIO = "tbl_usuario"
        private const val TABLE_CREATE_USUARIO = (
                "create table " + TABELA_USUARIO +
                        " (id integer PRIMARY KEY AUTOINCREMENT, nome text, email text, senha text);"
                )

        // TABELA DOAÇÃO
        private const val TABELA_DOACAO = "tbl_doacao"
        private const val TABLE_CREATE_DOACAO = (
                "create table " + TABELA_DOACAO +
                        " (id integer PRIMARY KEY AUTOINCREMENT, usuario_id integer, ong_id integer, nome_ong text, valor real, chave_pix text, data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP);"                )
    }
}