package datos

import modelo.Frase

interface RepositorioFrases {
    fun obtenerFraseAleatoria(): Frase
    fun obtenerTodas(): List<Frase>
}

class RepositorioFrasesMemoria : RepositorioFrases {
    
    // Lista de frases hardcodeada para empezar
    private val frases = listOf(
        Frase("Peliculas", "Lo que el viento se llevo", ""),
        Frase("Peliculas", "El feo, el bueno y el malo", "Complexión corporal"),
        Frase("Refranes", "Al que madruga, Dios le ayuda", "Horario"),
        Frase("Refranes", "Nunca es tarde si la dicha es buena", "Horario"),
        Frase("Refranes", "Quien siembra vientos, recoge tempestades", "Cultivo"),
        Frase("Refranes", "Quien mucho abarca, poco aprieta", "Ansia"),
        Frase("Refranes", "A caballo regalado, no le mires el dentado", "Regalo"),
        Frase("Refranes", "A cada cerdo le llega su San Martin", "Destino"),
        Frase("Refranes", "A Dios rogando y con el mazo dando", "Oración"),
        Frase("Refranes", "A falta de pan, buenas son tortas", "Alimento"),
        Frase("Peliculas", "El Padrino", "Mafia"),
        Frase("Peliculas", "La lista de Schindler", "Nazismo"),
        Frase("Peliculas", "El señor de los anillos", "Fantasía"),
        Frase("Peliculas", "El club de la lucha", "Desdoblamiento"),
        Frase("Peliculas", "El silencio de los corderos", "Asesino"),
        Frase("Peliculas", "El resplandor", "Hotel"),
        Frase("Frases celebres", "Darme un punto de apoyo y movere el mundo", "Física"),
        Frase("Frases celebres", "La ignorancia es la noche de la mente", "Conocimiento"),
        Frase("Frases celebres", "La vida es sueño", "Filosofía"),
        Frase("Frases celebres", "El corazon tiene razones que la razon ignora", "Amor"),
        Frase("Frases celebres", "Solo sé que no se nada", "Filosofía"),
        Frase("Frases celebres", "No hay peor sordo que el que no quiere oir", "Sentidos"),
        Frase("Frases celebres", "La vida no deberia medirse en cantidad sino en calidad", "Vida"),
        Frase("Frases celebres", "El hombre que mueve montañas comienza apartando piedrecitas", "Paciencia"),
        Frase("Frases celebres", "Haz el amor y no la guerra", "Amor"),
        Frase("Frases celebres", "Cada dia sabemos mas y entendemos menos", "Conocimiento"),
        Frase("Frases celebres", "Si das pescado a un hombre hambriento, le nutres una jornada", "Enseñanza"),
    )

    override fun obtenerFraseAleatoria(): Frase {
        // TODO: Devolver una frase al azar
        return frases.first() 
    }

    override fun obtenerTodas(): List<Frase> = frases
}
