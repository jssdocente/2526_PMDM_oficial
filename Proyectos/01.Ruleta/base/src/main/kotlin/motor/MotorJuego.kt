package motor

import datos.RepositorioFrases
import modelo.*

/**
 * Controla la lógica del juego. Mantiene el estado de la partida.
 * NO debe tener println ni scanner.
 */
class MotorJuego(
    private val repositorio: RepositorioFrases,
    val jugadores: List<Jugador>
) {
    // TODO: Definir estado actual
    // var turnoActual: Int = 0
    // var fraseActual: Frase? = null
    // var letrasDescubiertas: MutableSet<Char> = mutableSetOf()

    init {
        // TODO: Inicializar partida
    }

    fun iniciarRonda() {
        // TODO: Seleccionar frase, limpiar letras, resetear contadores de ronda
    }

    fun girarRuleta(): ResultadoRuleta {
        // TODO: Generar resultado aleatorio
        // Valores posibles: 50, 100, ..., Quiebra, PierdeTurno
        return ResultadoRuleta.Premio(0) // Placeholder
    }

    fun probarLetra(letra: Char): Boolean {
        // TODO: Comprobar si la letra está en la frase
        // Si está -> sumar dinero, devolver true
        // Si no está -> pasar turno, devolver false
        return false
    }

    fun resolverPanel(fraseIntento: String): Boolean {
        // TODO: Comprobar si la frase es exacta
        return false
    }

    // TODO: Más métodos para gestión de turno, cambio de jugador...
}
