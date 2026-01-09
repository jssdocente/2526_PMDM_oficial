package examen

class Equipo(val tipo: EquipoTipo, minBoleto: Int, maxBoleto: Int) {

    // TODO: Instanciar el Bombo propio del equipo usando minBoleto y maxBoleto

    // TODO: Definir lista mutable para almacenar los objetos Jugador

    // Banco de nombres para asignar aleatoriamente
    private val nombresPosibles = listOf("Hugo", "Lucía", "Martín", "Sofía", "Daniel", "Martina", "Pablo", "Julia", "Alejandro", "Paula")

    fun reclutarJugador(idJugador: Int): Jugador {
        /* TODO: Obtener el número del boleto del juegador (obtener del bombo),
             Crear un jugador obteniendo un nombre de jugador aleatorio (.ramdom) y agregar al equipo.
         */

        throw UnsupportedOperationException("No implementado")
    }

    fun getTodosLosJugadores(): List<Jugador> {
        // TODO: Devolver la lista lista completa de jugadores
        return emptyList()
    }

    fun getJugadoresVivos(): List<Jugador> {
        // TODO: Devuelve la lista de jugadores vivos. Puedes usar .filter o un bucle FOR.
        return emptyList()
    }
}

// --- MAIN EJERCICIO 2 ---
fun main() {
    println("\n--- TEST EJERCICIO 2 ---")
    // Alumno: Completa la prueba
    /*
    val equipoRojo = Equipo(EquipoTipo.ROJO, 1, 10)
    val j1 = equipoRojo.reclutarJugador(1)
    val j2 = equipoRojo.reclutarJugador(2)

    j1.eliminar()

    println("Total jugadores: ${equipoRojo.getTodosLosJugadores().size}") // 2
    println("Vivos: ${equipoRojo.getJugadoresVivos().size}") // 1
    */
}