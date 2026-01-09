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
        // ROJO: 1-33, VERDE: 34-66, AZUL: 67-100
        equipos.add(Equipo(EquipoTipo.ROJO, 1, 33))
        equipos.add(Equipo(EquipoTipo.VERDE, 34, 66))
        equipos.add(Equipo(EquipoTipo.AZUL, 67, 100))
    }

    fun iniciar(totalJugadores: Int) {
        // TODO: Repartir 'totalJugadores' entre los 3 equipos. Debe ser válido para cualquier número de jugadores.
        // TODO: Si el totalJugadores no es múltiplo de 3, emite una excepción indicando "número de jugadores debe ser múltiplo de 3".
        if (totalJugadores % 3 != 0) {
            throw IllegalArgumentException("El número de jugadores debe ser múltiplo de 3")
        }

        val jugadoresPorEquipo = totalJugadores / 3
        var idCounter = 1

        for (equipo in equipos) {
            for (i in 1..jugadoresPorEquipo) {
                equipo.reclutarJugador(idCounter)
                idCounter++
            }
        }
    }
    
    // Helper para saber cuantos vivos quedan en total
    fun contarVivos(): Int {
        var total = 0
        for (equipo in equipos) {
            total += equipo.getJugadoresVivos().size
        }
        return total
    }

    fun realizarTirada(): ResultadoTirada {
        // TODO: Sacar bola del bomboPrincipal, si algún equipo tiene un jugador con esa bola (número) debe ser eliminado.
        val bola = bomboPrincipal.sacarBola()
        
        // Buscar si alguien tiene esa bola
        var jugadorEncontrado: Jugador? = null
        
        // Recorremos todos los equipos y sus jugadores VIVOS
        // Un jugador muerto ya no juega, aunque tuviera el boleto ya murió.
        for (equipo in equipos) {
             val posibleVictima = equipo.getJugadoresVivos().find { it.numBoleto == bola }
             if (posibleVictima != null) {
                 jugadorEncontrado = posibleVictima
                 break // Solo un jugador puede tener un boleto único? El enunciado dice "número de boleto único (dentro del rango de su equipo)".
                 // Y los rangos son disjuntos (1-33, 34-66, 67-100). Así que sí, el boleto es único en todo el juego.
             }
        }
        
        val mensaje: String
        
        if (jugadorEncontrado != null) {
            // Eliminar (o intentar eliminar si es VIP)
            // Nota: Si es VIP, el método eliminar() imprimirá un mensaje por consola.
            // Nosotros construimos el mensaje de retorno.
            jugadorEncontrado.eliminar()
            
            if (jugadorEncontrado.isEliminado()) {
                mensaje = "❌ ¡Bola $bola extraída! Jugador ${jugadorEncontrado.nombre} ELIMINADO."
            } else {
                // Es VIP y sobrevivió
                mensaje = "🛡️ ¡Bola $bola extraída! Jugador ${jugadorEncontrado.nombre} SOBREVIVE usando su escudo."
            }
        } else {
             // Nadie tenía el boleto
             mensaje = "⚪ Bola $bola extraída. Nadie tenía este boleto."
        }

        // TODO: Devolver ResultadoTirada con la información de la Tirada.
        return ResultadoTirada(bola, mensaje, jugadorEncontrado)
    }

    fun buscarGanador(): Jugador? {
        // TODO: El Ganador es el último jugador de cualquier equipo no eliminado. Si hay +1 jugador, no hay ganador
        val vivos = mutableListOf<Jugador>()
        for (equipo in equipos) {
            vivos.addAll(equipo.getJugadoresVivos())
        }
        
        if (vivos.size == 1) {
            return vivos[0]
        }
        return null
    }

    // Método helper para imprimir estado (Ya implementado)
    fun imprimirEstadoEquipos() {
        //TODO: Imprimir estado de todos los equipos. "Equiipo ROJO: 5 vivos".
        println("\nEstado Inicial de los Equipos:")
        for (equipo in equipos) {
            val vivos = equipo.getJugadoresVivos()
            println("Equipo ${equipo.tipo}: ${vivos.size} vivos.")
            for (j in vivos) {
                println("  $j")
            }
        }
        println()
    }
}

// --- MAIN FINAL ---
fun main() {
    println("\n--- COMIENZA EL JUEGO DEL CALAMAR ---")

    val juego = JuegoCalamar()

    // 1. Iniciar con 9 jugadores
    try {
        juego.iniciar(9)
        juego.imprimirEstadoEquipos()
    
        println("--- INICIO DE LAS TIRADAS ---\n")
    
        // Dinámica del juego:
        // TODO:
        // El juego debe continuar hasta que no haya un Ganador.
        //    a) Realizar tirada
        //    b) Imprimir por consola el 'mensaje' del resultado de la tirada.
        //    d) Llamar a juego.buscarGanador().
        //       Si devuelve un jugador distinto de null -> imprimir ganador, poner tenemosGanador = true.
        //       Si devuelve null -> el juego continúa.
        
        var hayGanador = false
        while (!hayGanador) {
             // Verificación de seguridad por si se acaban las bolas (sería empate o todos muertos)
             // Aunque con 100 bolas y 9 jugadores es casi imposible que se acaben sin ganador, salvo que los VIPs gasten muchas.
             // No tengo acceso directo a si el bombo está vacío, pero sacarBola devuelve -1.
             
             val resultado = juego.realizarTirada()
             
             if (resultado.bolaExtraida == -1) {
                 println("⚠️ Se han acabado las bolas del bombo principal sin ganador único.")
                 break
             }
             
             println(resultado.mensaje)
             
             // Pequeña lógica para imprimir estado si quedan pocos (opcional, para que se parezca al ejemplo)
             if (juego.contarVivos() <= 2 && juego.contarVivos() > 0) {
                 println("\n*** SOLO QUEDAN ${juego.contarVivos()} JUGADORES VIVOS ***")
                 // Podríamos imprimir quiénes son, pero el método imprimirEstadoEquipos es un poco verbose.
             }
             
             val ganador = juego.buscarGanador()
             if (ganador != null) {
                 hayGanador = true
                 println("\n--- FIN DEL JUEGO ---")
                 println("\n🏆 ¡TENEMOS UN GANADOR! 🏆")
                 println("El ganador es: $ganador")
             } else {
                 if (juego.contarVivos() == 0) {
                     println("\n--- FIN DEL JUEGO ---")
                     println("☠️ TODOS HAN MUERTO. NO HAY GANADORES.")
                     break
                 }
             }
             
             // Thread.sleep(500) // Opcional para dar emoción
        }
        
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
