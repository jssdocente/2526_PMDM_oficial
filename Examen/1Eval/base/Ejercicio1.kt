package examen

import kotlin.random.Random

// 1. INTERFAZ
interface FuenteAzar {
    fun mezclar(): Unit
    fun sacarBola(): Int
}

// 2. CLASE BOMBO
// TODO: Debe implementar la interfaz FuenteAzar
class Bombo(private val min: Int, private val max: Int) {

    // TODO: Definir la estructura de datos para guardar los números (las bolas del bombo)
    private val bolas: MutableList<Int> = mutableListOf();

    init {
        // TODO: Rellenar el bombo con los números desde min al max
        // TODO: Mezclar las bolas del bombo (usa el método .shuffle)
    }

    // TODO: Implementar métodos de la interfaz
    // sacarBola() debe devolver el número y eliminarlo de la lista. (Investiga algún método de la estructura Lista que haga esto)
    // Si no quedan bolas, puede devolver -1 o lanzar una excepción (a tu elección).
}

// 3. ENUMERADO
enum class EquipoTipo(val id: Int) {
    // TODO: Definir los casos ROJO, VERDE, AZUL con sus IDs (1, 2, 3)
    ;

    fun getNombreIngles(): String {
        // TODO: Devolver "Red", "Green" o "Blue" según el caso (Usar when)
        return ""
    }
}

// 4. CLASE JUGADOR
// TODO: Definir constructor primario con: id, nombre, equipo (EquipoTipo), numBoleto
class Jugador {

    // TODO: Propiedad privada para saber si está eliminado (booleano). Inicialmente false.

    fun eliminar() {
        // TODO: eliminar jugador
    }

    fun isEliminado(): Boolean {
        // TODO: Devolver si jugador está eliminado
        return false
    }

    override fun toString(): String {
        // TODO: Retornar String formato: "(ID) Nombre [EquipoTipo] -> Boleto: X"
        // Ejemplo: "(1) Javier [ROJO] -> Boleto: 25"
        // Si está eliminado, añadir " (ELIMINADO)" al final.
        return ""
    }
}

// --- MAIN EJERCICIO 1 ---
fun main() {
    println("--- TEST EJERCICIO 1 ---")
    // Alumno: Descomenta y haz que esto funcione
    /*
    val bombo = Bombo(1, 10)
    println("Bola sacada: ${bombo.sacarBola()}")

    val jugador = Jugador(1, "Sonia", EquipoTipo.VERDE, 55)
    println(jugador)
    jugador.eliminar()
    println(jugador) // Debería indicar que está eliminado
    */
}