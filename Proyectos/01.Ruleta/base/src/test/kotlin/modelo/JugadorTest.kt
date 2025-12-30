package modelo

import motor.MotorJuego
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JugadorTest {

    // Clase concreta local para probar la clase abstracta Jugador
    class JugadorTestImpl(nombre: String) : Jugador(nombre) {
        override fun realizarMovimiento(motor: MotorJuego) {
            // No hace nada en tests
        }
    }

    @Test
    fun `sumarDinero incrementa el saldo`() {
        val jugador = JugadorTestImpl("TestPlayer")
        assertEquals(0, jugador.dineroAcumulado)
        
        jugador.sumarDinero(100)
        assertEquals(100, jugador.dineroAcumulado)
        
        jugador.sumarDinero(50)
        assertEquals(150, jugador.dineroAcumulado)
    }

    /* DESCOMENTAR CUANDO SE IMPLEMENTE QUIEBRA
    @Test
    fun `quiebra resetea el dinero de la ronda`() {
        val jugador = JugadorTestImpl("TestPlayer")
        jugador.sumarDinero(1000)
        
        jugador.quiebra()
        // Dependiendo de si quiebra resetea TOTAL o RONDA, ajustar aserción
        // Asumiendo que resetea ronda y dineroAcumulado incluye ronda:
        assertEquals(0, jugador.dineroAcumulado)
    }
    */
}
