package examen

import kotlin.random.Random

// 1. INTERFAZ
interface FuenteAzar {
    fun mezclar(): Unit
    fun sacarBola(): Int
}

// 2. CLASE BOMBO
// TODO: Debe implementar la interfaz FuenteAzar
class Bombo(private val min: Int, private val max: Int) : FuenteAzar {

    // TODO: Definir la estructura de datos para guardar los números (las bolas del bombo)
    private val bolas: MutableList<Int> = mutableListOf()

    init {
        // TODO: Rellenar el bombo con los números desde min al max
        for (i in min..max) {
            bolas.add(i)
        }
        // TODO: Mezclar las bolas del bombo (usa el método .shuffle)
        mezclar()
    }

    // TODO: Implementar métodos de la interfaz
    override fun mezclar() {
        bolas.shuffle()
    }

    // sacarBola() debe devolver el número y eliminarlo de la lista. (Investiga algún método de la estructura Lista que haga esto)
    // Si no quedan bolas, puede devolver -1 o lanzar una excepción (a tu elección).
    override fun sacarBola(): Int {
        if (bolas.isEmpty()) {
            return -1
        }
        return bolas.removeAt(0)
    }
}

// 3. ENUMERADO
enum class EquipoTipo(val id: Int) {
    // TODO: Definir los casos ROJO, VERDE, AZUL con sus IDs (1, 2, 3)
    ROJO(1),
    VERDE(2),
    AZUL(3);

    fun getNombreIngles(): String {
        // TODO: Devolver "Red", "Green" o "Blue" según el caso (Usar when)
        return when (this) {
            ROJO -> "Red"
            VERDE -> "Green"
            AZUL -> "Blue"
        }
    }
}

// 4. CLASE JUGADOR
// TODO: Definir constructor primario con: id, nombre, equipo (EquipoTipo), numBoleto
// Open para el Bonus
open class Jugador(val id: Int, val nombre: String, val equipo: EquipoTipo, val numBoleto: Int) {

    // TODO: Propiedad privada para saber si está eliminado (booleano). Inicialmente false.
    private var eliminado: Boolean = false

    // Open para el Bonus
    open fun eliminar() {
        // TODO: eliminar jugador
        eliminado = true
    }

    fun isEliminado(): Boolean {
        // TODO: Devolver si jugador está eliminado
        return eliminado
    }

    // Open para el Bonus
    override fun toString(): String {
        // TODO: Retornar String formato: "(ID) Nombre [EquipoTipo] -> Boleto: X"
        // Ejemplo: "(1) Javier [ROJO] -> Boleto: 25"
        // Si está eliminado, añadir " (ELIMINADO)" al final.
        var texto = "($id) $nombre [$equipo] -> Boleto: $numBoleto"
        if (isEliminado()) {
            texto += " (ELIMINADO)"
        }
        return texto
    }
}

// --- MAIN EJERCICIO 1 ---
fun main() {
    println("--- TEST EJERCICIO 1 ---")
    // Alumno: Descomenta y haz que esto funcione
    
    val bombo = Bombo(1, 10)
    println("Bola sacada: ${bombo.sacarBola()}")

    val jugador = Jugador(1, "Sonia", EquipoTipo.VERDE, 55)
    println(jugador)
    jugador.eliminar()
    println(jugador) // Debería indicar que está eliminado
    
}
