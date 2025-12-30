package modelo

/**
 * Representa el resultado de girar la ruleta.
 */
sealed interface ResultadoRuleta {
    data class Premio(val cantidad: Int) : ResultadoRuleta
    object Quiebra : ResultadoRuleta
    object PierdeTurno : ResultadoRuleta
}

/**
 * Representa el resultado de una acción del jugador (decir letra, resolver...).
 */
sealed interface ResultadoAccion {
    object Acierto : ResultadoAccion
    object Fallo : ResultadoAccion
    object PanelResuelto : ResultadoAccion
    data class Error(val mensaje: String) : ResultadoAccion
}
