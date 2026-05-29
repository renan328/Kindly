package com.example.kindly

// Modelo de dados que representa uma ONG
data class Ong(
    val id: Int,
    val nome: String,
    val descricao: String,
    val corBackgroundHex: String,
)

// Repositório em memória para testes
object OngRepository {
    private val listaOngs = listOf(
        Ong(1, "Felizes Animais", "Cuidado, resgate e acolhimento de animais abandonados.", "#5770A4"),
        Ong(2, "ONG Sustentável", "Ações focadas em reflorestamento e educação ambiental.", "#2D3E50"),
        Ong(3, "Educação para Todos", "Apoio escolar e inclusão digital para jovens da periferia.", "#A45757"),
        Ong(4, "Amigos do Peito", "Distribuição de marmitas e agasalhos para pessoas em situação de rua.", "#4A7A57"),
        Ong(5, "Sorriso de Criança", "Atividades culturais e esportivas no contraturno escolar.", "#8A57A4")
    )

    fun obterTodas(): List<Ong> {
        return listaOngs
    }

    fun obterPorId(id: Int): Ong? {
        return listaOngs.find { it.id == id }
    }
}