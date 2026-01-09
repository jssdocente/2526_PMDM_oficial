package examen

class Equipo(val tipo: EquipoTipo, minBoleto: Int, maxBoleto: Int) {

    // TODO: Instanciar el Bombo propio del equipo usando minBoleto y maxBoleto
    private val bombo: Bombo = Bombo(minBoleto, maxBoleto)

    // TODO: Definir lista mutable para almacenar los objetos Jugador
    private val jugadores: MutableList<Jugador> = mutableListOf()

    // Banco de nombres para asignar aleatoriamente
    private val nombresPosibles = listOf("Hugo", "Lucía", "Martín", "Sofía", "Daniel", "Martina", "Pablo", "Julia", "Alejandro", "Paula")

    fun reclutarJugador(idJugador: Int): Jugador {
        /* TODO: Obtener el número del boleto del juegador (obtener del bombo),
             Crear un jugador obteniendo un nombre de jugador aleatorio (.ramdom) y agregar al equipo.
         */
        
        val boleto = bombo.sacarBola()
        val nombre = nombresPosibles.random()
        
        // Lógica BONUS: Si ID múltiplo de 3, es VIP.
        val nuevoJugador = if (idJugador % 3 == 0) {
            JugadorVIP(idJugador, nombre, tipo, boleto)
        } else {
            Jugador(idJugador, nombre, tipo, boleto)
        }
        
        jugadores.add(nuevoJugador)
        return nuevoJugador
    }

    fun getTodosLosJugadores(): List<Jugador> {
        // TODO: Devolver la lista lista completa de jugadores
        return jugadores
    }

    fun getJugadoresVivos(): List<Jugador> {
        // TODO: Devuelve la lista de jugadores vivos. Puedes usar .filter o un bucle FOR.
        return jugadores.filter { !it.isEliminado() }
    }
}

// --- MAIN EJERCICIO 2 ---
fun main() {
    println("\n--- TEST EJERCICIO 2 ---")
    // Alumno: Completa la prueba
    
    val equipoRojo = Equipo(EquipoTipo.ROJO, 1, 10)
    val j1 = equipoRojo.reclutarJugador(1)
    val j2 = equipoRojo.reclutarJugador(2)
    val j3 = equipoRojo.reclutarJugador(3) // Debería ser VIP
    
    println("J1: $j1")
    println("J3 (VIP?): $j3")

    j1.eliminar()
    println("J1 tras eliminar: $j1")
    
    // Probar escudo VIP
    println("Eliminando J3 primera vez...")
    j3.eliminar()
    println("J3 tras eliminar 1 vez: $j3")
    println("Eliminando J3 segunda vez...")
    j3.eliminar()
    println("J3 tras eliminar 2 vez: $j3")

    println("Total jugadores: ${equipoRojo.getTodosLosJugadores().size}") // 3
    println("Vivos: ${equipoRojo.getJugadoresVivos().size}") // 1 (j2)
    
}
