package modelo

import motor.MotorJuego

/**
 * Representa a un jugador genérico.
 * @param nombre Nombre del jugador
 */
abstract class Jugador(val nombre: String) {
    // Dinero acumulado total en la partida
    var dineroAcumulado: Int = 0
        protected set // Solo modificable desde dentro o subclases (si se cambia a protected set)

    // TODO: Añadir atributo para el dinero de la ronda actual si es necesario

    /**
     * Añade dinero al acumulado del jugador.
     */
    fun sumarDinero(cantidad: Int) {
        // TODO: Implementar
    }

    /**
     * Deja el dinero de la ronda a 0 (Quiebra).
     */
    fun quiebra() {
        // TODO: Implementar lógica de quiebra (resetear dinero ronda? o total?)
        // Según enunciado: pierde dinero de *esta* ronda.
    }

    /**
     * Método abstracto que define cómo juega este jugador.
     * La UI o el Motor llamará a esto cuando sea su turno.
     *
     * @param motor Referencia al motor para consultar estado o realizar acciones.
     */
    abstract fun realizarMovimiento(motor: MotorJuego)
}
