package modelo

import motor.MotorJuego

class JugadorIA(nombre: String) : Jugador(nombre) {

    override fun realizarMovimiento(motor: MotorJuego) {
        println("🤖 $nombre está pensando...")
        
        // Simulación de "pensar" (Bloqueante por ahora)
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        // TODO: Implementar lógica de la IA
        // 1. Decidir si tirar ruleta, comprar vocal o resolver.
        // 2. Si tira ruleta y sale premio -> elegir consonante aleatoria no usada.
        
        // Pista: motor.letrasDisponibles()
    }
}
