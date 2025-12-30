package modelo

data class Frase(
    val categoria: String,
    val texto: String, // La frase en sí
    val pista: String = ""
)
