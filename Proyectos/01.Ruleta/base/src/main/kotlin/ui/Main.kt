package ui

import motor.MotorJuego
import datos.RepositorioFrasesMemoria
import modelo.JugadorIA
// import modelo.JugadorHumano

fun main() {
    println("🎉 Bienvenido a La Ruleta de la Fortuna 🎉")

    // 1. Configuración inicial
    val repo = RepositorioFrasesMemoria()
    
    // TODO: Preguntar número de jugadores y nombres
    val jugadores = listOf(
        JugadorIA("Bot-1"),
        JugadorIA("Bot-2")
    )

    // 2. Iniciar Motor
    val motor = MotorJuego(repo, jugadores)
    motor.iniciarRonda()

    // 3. Bucle de juego
    var partidaFinalizada = false
    while (!partidaFinalizada) {
        // TODO: Mostrar estado del panel (guiones y letras)
        
        // TODO: Pedir acción al jugador actual (o ejecutar IA)
        
        // Ejemplo pseudo-código:
        // val jugadorActual = motor.getJugadorActual()
        // jugadorActual.realizarMovimiento(motor)
        
        // TODO: Comprobar condiciones de fin de partida
        partidaFinalizada = true // Evitar bucle infinito en skeleton
    }
}
