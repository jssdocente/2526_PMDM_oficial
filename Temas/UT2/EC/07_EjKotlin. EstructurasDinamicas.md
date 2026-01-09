# 🏋️‍♀️ Ejercicios: Estructuras de Datos y Clases

## Ejercicio 1: La Playlist de Música (Listas Mutables)

**Objetivo:** Entender cómo funcionan las `MutableList` (añadir, borrar, modificar).
**Contexto:** Estás creando el reproductor de música de tu App. Necesitas gestionar la cola de reproducción.

1.  Define una `data class Cancion(val titulo: String, val artista: String, val duracionSegundos: Int)`.
2.  Crea una lista mutable vacía llamada `playlist`.
3.  Añade 3 canciones.
4.  Imprime la lista.
5.  **Reto:** Alguien se ha equivocado. Elimina todas las canciones que duren más de 300 segundos (5 minutos) usando `removeIf`.

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
// main.kt
fun main() {
    println("=== EJERCICIO 1: Playlist ===")
    
    // 1. Data Class (Ya definida en plantilla)
    // 2. Crear lista mutable
    val playlist = mutableListOf<Cancion>()
    
    // 3. Añadir canciones
    playlist.add(Cancion("Bohemian Rhapsody", "Queen", 354))
    playlist.add(Cancion("Imagine", "John Lennon", 183))
    playlist.add(Cancion("Stairway to Heaven", "Led Zeppelin", 482))
    
    // 4. Imprimir
    println("Playlist inicial: $playlist")

    // 5. Reto: Eliminar canciones largas (> 300s)
    // Nota: removeIf es Java 8+. En Kotlin puro 'removeAll' con lambda funciona igual y es más común.
    // playlist.removeIf { it.duracionSegundos > 300 } 
    playlist.removeAll { it.duracionSegundos > 300 }

    println("Playlist filtrada: $playlist")
    println("-------------------------------------\n")
}
```
</details>

---

## Ejercicio 2: Base de Datos de Empleados (Mapas)

**Objetivo:** Dominar la búsqueda rápida por Clave (ID) y el manejo de nulos.
**Contexto:** Tienes un sistema de RRHH. Identificamos a los empleados por su DNI (String).

1.  Define `data class Empleado(val nombre: String, val puesto: String)`.
2.  Crea un mapa: `val empleados: MutableMap<String, Empleado> = mutableMapOf()`.
3.  Inserta 2 empleados inventando sus DNIs como clave.
4.  Simula una búsqueda: Crea una variable `dniBuscado` que no exista en el mapa.
5.  Intenta recuperar el empleado y usa el operador Elvis (`?:`) para imprimir "Empleado no encontrado" si devuelve `null`.
6.  Busca un DNI que sí exista e imprime su nombre.

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
// main.kt
fun main() {
    println("=== EJERCICIO 2: Mapa Empleados ===")
    
    // 2. Crear mapa mutable
    val empleados: MutableMap<String, Empleado> = mutableMapOf()
    
    // 3. Insertar empleados
    empleados["12345678A"] = Empleado("Ana Ruiz", "Gerente")
    empleados["87654321B"] = Empleado("Carlos Pérez", "Desarrollador")
    
    // 4. Simula búsqueda fallida
    val dniBuscado = "00000000Z"
    
    // 5. Intentar recuperar y usar Elvis
    val empleadoEncontrado = empleados[dniBuscado]
    println("Buscando $dniBuscado: ${empleadoEncontrado ?: "Empleado no encontrado"}")

    // 6. Buscar uno existente
    val dniExistente = "12345678A"
    // Usamos ?.let para ejecutar código solo si no es nulo
    empleados[dniExistente]?.let {
        println("Encontrado: ${it.nombre}")
    }
    
    println("-------------------------------------\n")
}
```
</details>

---

## Ejercicio 3: Análisis de Ventas (Filter y Map) ⭐️ _Vital para Compose_

**Objetivo:** Transformar datos crudos en datos listos para la Interfaz de Usuario (UI).
**Contexto:** Recibes una lista de ventas del servidor. Quieres mostrar solo las ventas grandes y solo los nombres de los clientes.

1.  Usa esta clase y lista ya definida:

    ```kotlin
    data class Venta(val cliente: String, val importe: Double, val esVIP: Boolean)

    val ventas = listOf(
        Venta("Lucía", 150.0, true),
        Venta("Marcos", 40.0, false),
        Venta("Elena", 200.0, true),
        Venta("Juan", 10.0, false)
    )
    ```

2.  **Paso A (Filtrar):** Crea una lista `ventasVip` que contenga solo las ventas donde `esVIP` sea `true` **Y** el importe sea mayor a 100.0.
3.  **Paso B (Mapear):** A partir de la lista original, crea una lista de Strings `nombresClientes` que contenga solo los nombres en mayúsculas.
4.  **Paso C (Sumar):** Calcula el total de dinero de todas las ventas usando `sumOf`.

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
// main.kt
fun main() {
    println("=== EJERCICIO 3: Análisis Ventas ===")
    val ventas = listOf(
        Venta("Lucía", 150.0, true),
        Venta("Marcos", 40.0, false),
        Venta("Elena", 200.0, true),
        Venta("Juan", 10.0, false)
    )

    // Paso A: Filtrar VIPs con importe > 100
    val ventasVip = ventas.filter { it.esVIP && it.importe > 100.0 }
    println("Ventas VIP importantes: $ventasVip")

    // Paso B: Mapear a nombres en mayúsculas
    // Nota: map transforma la lista de Ventas en lista de Strings
    val nombresClientes = ventas.map { it.cliente.uppercase() }
    println("Clientes: $nombresClientes")

    // Paso C: Sumar total
    val total = ventas.sumOf { it.importe }
    println("Total ventas: $total")
    
    println("-------------------------------------\n")
}
```
</details>

---

## Ejercicio 4: Gestor de Tareas (Encapsulamiento)

**Objetivo:** Crear una "Mini-lógica" protegida. Esto es la base de los **ViewModels**.
**Contexto:** Una App de "To-Do List". No queremos que desde fuera se pueda borrar la lista entera por error, así que la protegemos en una clase.

1.  Define `data class Tarea(val id: Int, val descripcion: String, var completada: Boolean = false)`. _(Nota el var en completada)_.
2.  Crea una clase `GestorDeTareas`.
    - Debe tener una propiedad **privada** `private val listaTareas = mutableListOf<Tarea>()`.
    - Crea una función `agregarTarea(t: Tarea)` que la añada a la lista.
    - Crea una función `marcarComoCompletada(id: Int)`. Debe buscar la tarea por ID y poner `completada = true`.
    - Crea una función `obtenerPendientes(): List<Tarea>` que devuelva solo las tareas que NO están completadas (usa `filter`).

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
// main.kt
fun main() {
    println("=== EJERCICIO 4: Gestor Tareas ===")
    val gestor = GestorDeTareas()
    
    // Agregar tareas
    gestor.agregarTarea(Tarea(1, "Aprender Kotlin"))
    gestor.agregarTarea(Tarea(2, "Hacer deploy"))
    
    println("Pendientes iniciales: ${gestor.obtenerPendientes()}")
    
    // Completar una
    gestor.marcarComoCompletada(1)
    
    println("Pendientes tras completar ID 1: ${gestor.obtenerPendientes()}")
    println("-------------------------------------\n")
}

class GestorDeTareas {
    // Lista privada: Nadie desde fuera puede hacer .clear() o .remove()
    private val listaTareas = mutableListOf<Tarea>()

    fun agregarTarea(t: Tarea) {
        listaTareas.add(t)
    }

    fun marcarComoCompletada(id: Int) {
        // Buscamos la tarea. find devuelve Tarea? (nullable)
        val tarea = listaTareas.find { it.id == id }
        
        // Si existe (no es nulo), actualizamos.
        // Al ser una data class (referencia), se actualiza dentro de la lista.
        tarea?.completada = true
    }

    fun obtenerPendientes(): List<Tarea> {
        // Retornamos una lista nueva solo con las pendientes
        return listaTareas.filter { !it.completada }
    }
}
```
</details>

---

# 💻 Plantilla de Código (Main.kt)

Copia esto en tu IDE y completa los `TODO`.

```kotlin
// --- DEFINICIÓN DE CLASES ---
data class Cancion(val titulo: String, val artista: String, val duracionSegundos: Int)
data class Empleado(val nombre: String, val puesto: String)
data class Venta(val cliente: String, val importe: Double, val esVIP: Boolean)
data class Tarea(val id: Int, val descripcion: String, var completada: Boolean = false)

// --- CLASE DEL EJERCICIO 4 ---
class GestorDeTareas {
    private val listaTareas = mutableListOf<Tarea>()

    fun agregarTarea(t: Tarea) {
        // TODO: Añadir a la lista
    }

    fun marcarComoCompletada(id: Int) {
        // TODO: Buscar la tarea por ID.
        // PISTA: Usa listaTareas.find { it.id == id }
        // Si la encuentras (no es null), cambia su propiedad completada a true
    }

    fun obtenerPendientes(): List<Tarea> {
        // TODO: Retornar solo las tareas donde completada == false
        return emptyList() // Cambia esto
    }

    fun imprimirEstado() {
        println("Tareas actuales: $listaTareas")
    }
}

// --- MAIN PRINCIPAL ---
fun main() {
    println("=== EJERCICIO 1: Playlist ===")
    // TODO: Resuelve el ejercicio 1 aquí


    println("\n=== EJERCICIO 2: Mapa Empleados ===")
    // TODO: Resuelve el ejercicio 2 aquí


    println("\n=== EJERCICIO 3: Análisis Ventas ===")
    val ventas = listOf(
        Venta("Lucía", 150.0, true),
        Venta("Marcos", 40.0, false),
        Venta("Elena", 200.0, true),
        Venta("Juan", 10.0, false)
    )
    // TODO: Resuelve el ejercicio 3 aquí


    println("\n=== EJERCICIO 4: Gestor Tareas ===")
    val gestor = GestorDeTareas()
    gestor.agregarTarea(Tarea(1, "Estudiar Kotlin"))
    gestor.agregarTarea(Tarea(2, "Hacer la compra"))

    println("Pendientes antes de completar:")
    println(gestor.obtenerPendientes())

    gestor.marcarComoCompletada(1)

    println("Pendientes tras completar la 1:")
    println(gestor.obtenerPendientes())
}
```

---

### 💡 Pistas del Profesor

- **Para el Ejercicio 1:** `removeIf { it.condicion }` modifica la lista original. Ten cuidado.
- **Para el Ejercicio 2:** Recuerda que `mapa["clave"]` devuelve un tipo nullable (`Empleado?`). ¡Usa `?.` o `?:`!
- **Para el Ejercicio 4:** Cuando usas `find`, te devuelve el objeto si existe. Como `Tarea` es una clase, es una referencia. Si modificas el objeto encontrado, se modifica también dentro de la lista. **¡Magia de las referencias!**
