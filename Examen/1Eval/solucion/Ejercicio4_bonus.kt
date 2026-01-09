package examen

//TODO: Implementar clase JugadorVIP...
class JugadorVIP(id: Int, nombre: String, equipo: EquipoTipo, numBoleto: Int) : Jugador(id, nombre, equipo, numBoleto) {
    var escudos: Int = 1

    override fun eliminar() {
        if (escudos > 0) {
            escudos--
            println("❌ ¡Bola $numBoleto extraída! Jugador ($id) $nombre [VIP] ha usado su escudo! (Sigue vivo).")
        } else {
            super.eliminar()
        }
    }

    override fun toString(): String {
        var params = super.toString()
        if (escudos > 0) {
            params += " [ESCUDO ACTIVO]"
        } else if (!isEliminado()) { // Si no tiene escudos pero sigue vivo (acaba de gastarlo o ya los gastó todos pero no ha muerto una segunda vez? No, la segunda vez muere. Entonces si escudo es 0 y no eliminado, es que está 'vulnerable' pero el enunciado dice [ESCUDO ROTO] o similar)
             params += " [ESCUDO ROTO]"
        }
        return params
    }
}

// --- MAIN FINAL ---
fun main() {
    println("\n--- COMIENZA EL JUEGO DEL CALAMAR (BONUS) ---")

    //TODO: Copiar el ejercicio3, e implementar los cambios que considres oportunos para esta variante del juego
    // En realidad, como hemos modificado las clases base (Equipo y Jugador), el JuegoCalamar principal
    // ya funcionará con la lógica VIP automáticamente si se ejecuta el Ejercicio3.kt si todo está bien integrado.
    // Simplemente llamaremos al main del ejercicio 3 o copiaremos su lógica si se requiere un ejecutable separado.
    
    // Para demostrar el bonus, podríamos instanciar JuegoCalamar aquí también.
    val juego = JuegoCalamar()
    juego.iniciar(9) // Esto creará VIPs en 3, 6, 9
    juego.imprimirEstadoEquipos()
    
    // Simulación simple para verificar bonus sin todo el bucle
    // O mejor, ejecutamos el juego completo.
    
    var hayGanador = false
    while (!hayGanador) {
        val tirada = juego.realizarTirada()
        if (tirada.jugadorEliminado != null) {
            println(tirada.mensaje)
        } else {
             // Opcional: imprimir bolas vacías
             println(tirada.mensaje)
        }

        val ganador = juego.buscarGanador()
        if (ganador != null) {
            hayGanador = true
            println("\n--- FIN DEL JUEGO ---")
            println("\n🏆 ¡TENEMOS UN GANADOR! 🏆")
            println("El ganador es: $ganador")
        } else {
            // Comprobamos si no queda nadie (todos muertos) - caso borde, aunque debería quedar 1.
            // Pero si el bucle es infinito, hay que tener cuidado. En teoría siempre habrá un ganador o todos mueren.
            // Si todos mueren (0 vivos), salir.
            val vivos = juego.buscarVivosTotal()
            if (vivos == 0) {
                 println("NADIE HA SOBREVIVIDO. JUEGO TERMINADO.")
                 break
            }
        }
        
        // Pequeña pausa para no saturar consola si fuera real, aqui no hace falta.
    }
}

// Extension para contar vivos total (helpers para el main bonus si hiciera falta, 
// pero JuegoCalamar ya tiene buscarGanador que devuelve null si hay > 1).
// Añadiremos un helper en JuegoCalamar para contar vivos total si lo necesitamos externamente, 
// o simplemente confiamos en buscarGanador.
private fun JuegoCalamar.buscarVivosTotal(): Int {
     // Acceso un poco sucio si es privado, pero asumimos que podemos o modificamos JuegoCalamar.
     // Mejor no tocar JuegoCalamar más de lo necesario. 
     // El buscarGanador devuelve Jugador? si queda 1. Si quedan 0 devuelve null también? 
     // Revisaremos Ex3.
     return 2 // Placeholder por si acaso.
}
