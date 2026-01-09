package examen

// 1. DATA CLASS para la respuesta de la tirada
data class ResultadoTirada(
    val bolaExtraida: Int,
    val mensaje: String,
    val jugadorEliminado: Jugador? // Será null si nadie tenía el boleto
)

class JuegoCalamar {
    private val bomboPrincipal = Bombo(1, 100)
    private val equipos: MutableList<Equipo> = mutableListOf()

    init {
        //TODO: Configurar los equipos según el enunciado y agregar a Equipos.
    }

    fun iniciar(totalJugadores: Int) {
        // TODO: Repartir 'totalJugadores' entre los 3 equipos. Debe ser válido para cualquier número de jugadores.
        // TODO: Si el totalJugadores no es múltiplo de 3, emite una excepción indicando "número de jugadores debe ser múltiplo de 3".
        // Ejemplo: Si son 9, crear 3 en cada equipo.
    }

    fun realizarTirada(): ResultadoTirada {
        // TODO: Sacar bola del bomboPrincipal, si algún equipo tiene un jugador con esa bola (número) debe ser eliminado.
        // TODO: Devolver ResultadoTirada con la información de la Tirada.
        // TODO: Ejemplo1: "Bola 58 extraída! Jugador Paco! ELIMINADO
        // TODO: Ejemplo2: "Bola 58 extraída! Naide tenía ese boleto.

        return ResultadoTirada(0, "Error no implementado", null)
    }

    fun buscarGanador(): Jugador? {
        // TODO: El Ganador es el último jugador de cualquier equipo no eliminado. Si hay +1 jugador, no hay ganador
        return null
    }

    // Método helper para imprimir estado (Ya implementado)
    fun imprimirEstadoEquipos() {
        //TODO: Imprimir estado de todos los equipos. "Equiipo ROJO: 5 vivos".
    }
}

// --- MAIN FINAL ---
fun main() {
    println("\n--- COMIENZA EL JUEGO DEL CALAMAR ---")

    val juego = JuegoCalamar()

    // 1. Iniciar con 9 jugadores
    juego.iniciar(9)
    juego.imprimirEstadoEquipos()

    // Dinámica del juego:
    // TODO:
    // El juego debe continuar hasta que no haya un Ganador.
    //    a) Realizar tirada
    //    b) Imprimir por consola el 'mensaje' del resultado de la tirada.
    //    d) Llamar a juego.buscarGanador().
    //       Si devuelve un jugador distinto de null -> imprimir ganador, poner tenemosGanador = true.
    //       Si devuelve null -> el juego continúa.

    // Una vez finalizado el Juego debe mostrar el Ganador
    //"🏆 ¡TENEMOS UN GANADOR! 🏆"
    //"El ganador es: ...
    //"Equipo: ...)

    println("--- FIN DEL JUEGO ---")
}

