# Ejercicios: Kotlin Flows

Los Flows son flujos de datos asíncronos que emiten múltiples valores de forma secuencial. Son fundamentales en aplicaciones reactivas y modernas con Android y Jetpack Compose.

>Video explicativo: [Guía Completa de Kotlin Flows: Flows, StateFlows y SharedFlows](https://www.youtube.com/embed/vGgB4wBjM-c)

## Ejercicio 1: El Grifo de Datos (Flow Básico y Collect)

Un `Flow` es como un grifo: define cómo sale el agua, pero el agua no fluye hasta que alguien abre el grifo (con `collect`).

**Tarea:**

1.  Crea una función llamada `generarNumeros()` que devuelva un `Flow<Int>`.
2.  Dentro del flow, usa un bucle para emitir los números del 1 al 5.
3.  Simula un retraso de 400ms entre cada emisión usando `delay()`.
4.  En la función `main`, recolecta el flow e imprime cada número recibido.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 1: El Grifo de Datos ---")
    // Llama a generarNumeros y recolecta sus valores
    
    println("--------------------------------------\n")
}
```

**Pistas:**

- Usa el constructor `flow { ... }` para crear el flujo.
- Recuerda que para usar `delay` y `collect`, necesitas estar en un entorno de corrutinas (`runBlocking` en el main).
- La función `emit(valor)` se usa para enviar datos por la tubería.

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 1: El Grifo de Datos ---")
    
    val flowNumeros = generarNumeros()
    
    println("Empezando a recolectar...")
    flowNumeros.collect { valor ->
        println("Recibido: $valor")
    }
    
    println("--------------------------------------\n")
}

fun generarNumeros(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(400)
        emit(i)
    }
}
```
</details>

## Ejercicio 2: Filtrando la Tubería (Operadores Intermedios)

Los operadores como `filter` y `map` nos permiten transformar los datos mientras viajan por el Flow sin llegar a detenerlo.

**Tarea:**

1.  Crea un Flow a partir de una lista de nombres: `"Ana", "Juan", "Alberto", "Maria", "Andrés"`. (Pista: usa `.asFlow()`).
2.  Aplica un filtro para dejar pasar solo los nombres que empiecen por la letra 'A'.
3.  Transforma cada nombre a mayúsculas usando `map`.
4.  Recolecta e imprime los resultados en el `main`.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 2: Filtrado de Nombres ---")
    val nombres = listOf("Ana", "Juan", "Alberto", "Maria", "Andrés")
    
    // Crea el flow, filtra, mapea y recolecta
    
    println("----------------------------------------\n")
}
```

**Pistas:**

- `asFlow()` convierte cualquier colección en un flujo de datos.
- Los operadores intermedios se pueden encadenar: `.filter { ... }.map { ... }`.

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 2: Filtrado de Nombres ---")
    val nombres = listOf("Ana", "Juan", "Alberto", "Maria", "Andrés")
    
    nombres.asFlow()
        .filter { it.startsWith("A") }
        .map { it.uppercase() }
        .collect { nombre ->
            println("Procesado: $nombre")
        }
    
    println("----------------------------------------\n")
}
```
</details>

## Ejercicio 3: Transformación Compleja (Operador Transform)

A veces `map` se queda corto porque solo permite emitir un valor por cada valor recibido. `transform` permite emitir múltiples valores, o incluso no emitir ninguno, para cada elemento de entrada.

**Tarea:**

1.  Dado un Flow de IDs de pedidos (Int): `101, 102, 103`.
2.  Usa `transform` para que, por cada ID:
    - Emita un mensaje "Iniciando proceso de pedido X".
    - Simule una espera de 300ms.
    - Emita un mensaje "Pedido X completado".
3.  Recolecta e imprime los mensajes.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 3: Transformación de Pedidos ---")
    val pedidos = (101..103).asFlow()
    
    // Usa transform para generar múltiples emisiones por cada pedido
    
    println("----------------------------------------------\n")
}
```

**Pistas:**

- Dentro de `transform { it -> ... }`, puedes llamar a `emit()` tantas veces como quieras.

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 3: Transformación de Pedidos ---")
    val pedidos = (101..103).asFlow()
    
    pedidos.transform { id ->
        emit("Iniciando proceso de pedido $id")
        delay(300)
        emit("Pedido $id completado")
    }.collect {
        println(it)
    }
    
    println("----------------------------------------------\n")
}
```
</details>

## Ejercicio 4: El Estado de la UI (StateFlow)

En Android/Compose, usamos `StateFlow` para representar un estado que la UI debe observar. A diferencia de un Flow normal, `StateFlow` siempre tiene un valor actual.

**Tarea:**

1.  Crea una clase `ContadorViewModel`.
2.  Dentro, define un `MutableStateFlow` privado de tipo `Int` inicializado en 0.
3.  Expón una propiedad pública de tipo `StateFlow<Int>` usando `.asStateFlow()`.
4.  Crea una función `incrementar()` que sume 1 al valor actual.
5.  En el `main`, crea una corrutina para observar los cambios e imprime cada nuevo valor.
6.  Llama a `incrementar()` varias veces.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 4: StateFlow Contador ---")
    val vm = ContadorViewModel()

    // Lanzar una corrutina para "escuchar" los cambios (collect)
    val job = launch {
        // Recolectar del StateFlow
    }

    delay(100)
    vm.incrementar()
    delay(100)
    vm.incrementar()
    
    delay(100)
    job.cancel() // Detener la escucha
    println("---------------------------------------\n")
}
```

**Pistas:**

- `_state.value` permite acceder o modificar el valor actual del `MutableStateFlow`.
- Usamos `launch` para que el `collect` no bloquee la ejecución de los siguientes `incrementar()`.

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
class ContadorViewModel {
    private val _contador = MutableStateFlow(0)
    val contador: StateFlow<Int> = _contador.asStateFlow()

    fun incrementar() {
        _contador.value++
    }
}

fun main() = runBlocking {
    println("--- EJERCICIO 4: StateFlow Contador ---")
    val vm = ContadorViewModel()

    val job = launch {
        vm.contador.collect { valor ->
            println("[UI] Valor del contador: $valor")
        }
    }

    delay(100)
    vm.incrementar()
    delay(100)
    vm.incrementar()
    delay(100)
    vm.incrementar()
    
    delay(100)
    job.cancel() 
    println("---------------------------------------\n")
}
```
</details>

## Ejercicio 5: Actualización Atómica de Objetos (.update)

Cuando el estado es un objeto complejo (un `data class`), usamos `.update` para generar una nueva versión del estado de forma segura e inmutable.

**Tarea:**

1.  Dada la clase `data class UserState(val name: String, val points: Int)`.
2.  Crea un `MutableStateFlow` con un usuario inicial: `"Invitado", 0`.
3.  Crea una función `ganarPuntos(puntos: Int)` que use `.update { ... }` para sumar puntos al usuario actual manteniendo su nombre.
4.  En el `main`, recolecta los cambios e imprime el estado completo cada vez.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 5: Actualización con .update ---")
    
    // Definir estado inicial y observar
    
    // Simular acciones
    // ganarPuntos(50)
    // ganarPuntos(20)

    println("----------------------------------------------\n")
}
```

**Pistas:**

- Dentro de `.update { it -> ... }`, `it` es el estado actual.
- Usa `it.copy(...)` para crear la nueva instancia modificada.

<details>
<summary><b>Solución Ejercicio 5</b></summary>

```kotlin
data class UserState(val name: String, val points: Int)

fun main() = runBlocking {
    println("--- EJERCICIO 5: Actualización con .update ---")
    
    val userState = MutableStateFlow(UserState("Invitado", 0))

    val job = launch {
        userState.collect { state ->
            println("Estado actualizado: $state")
        }
    }

    fun ganarPuntos(puntos: Int) {
        userState.update { currentState ->
            currentState.copy(points = currentState.points + puntos)
        }
    }

    delay(100)
    ganarPuntos(50)
    delay(100)
    ganarPuntos(20)
    
    delay(100)
    job.cancel()
    println("----------------------------------------------\n")
}
```
</details>

---

**¡Enhorabuena!** Has completado los ejercicios fundamentales de Flows. Estos patrones son los que usarás a diario para gestionar datos y estados en tus aplicaciones con Kotlin.