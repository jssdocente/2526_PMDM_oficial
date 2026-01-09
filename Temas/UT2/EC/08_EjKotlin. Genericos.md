# Ejercicios: Genéricos en Kotlin

## Ejercicio 1: Almacén Genérico Simple

Imaginad que necesitáis un almacén que pueda guardar cualquier tipo de objeto (productos, clientes, pedidos, etc.).

**Tarea:**

1.  Crea una clase genérica llamada `Almacen<T>` con las siguientes características:
    - Debe tener una lista mutable interna (`private val items: MutableList<T>`) para almacenar los objetos.
    - Un método `añadirItem(item: T)` que añada un objeto a la lista.
    - Un método `obtenerItem(index: Int): T?` que devuelva un objeto por su índice, o `null` si el índice no es válido.
    - Un método `listarItems()` que imprima todos los objetos almacenados.
    - Un método `contarItems(): Int` que devuelva el número de objetos en el almacén.
- Otra para almacenar `Int` (ej. números de pedido).
3.  Añade algunos elementos a cada almacén, lista sus contenidos y obtén un elemento por índice.

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 1: Almacén Genérico ---")

    // 1. Almacén de Strings
    val almacenProductos = Almacen<String>()
    almacenProductos.añadirItem("Monitor")
    almacenProductos.añadirItem("Teclado")
    almacenProductos.listarItems()
    println("Producto índice 1: ${almacenProductos.obtenerItem(1)}")

    println()

    // 2. Almacén de Enteros
    val almacenPedidos = Almacen<Int>()
    almacenPedidos.añadirItem(1001)
    almacenPedidos.añadirItem(1002)
    almacenPedidos.añadirItem(1003)
    almacenPedidos.listarItems()
    println("Total pedidos: ${almacenPedidos.contarItems()}")
    
    println("-------------------------------------\n")
}

// Clase Genérica T
class Almacen<T> {
    private val items = mutableListOf<T>() // Lista interna protegida

    fun añadirItem(item: T) {
        items.add(item)
    }

    fun obtenerItem(index: Int): T? {
        return if (index in 0 until items.size) items[index] else null
    }

    fun listarItems() {
        println("Contenido del Almacén: $items")
    }

    fun contarItems(): Int = items.size
}
```
</details>

**Pista:** Pensad cómo Compose podría usar un `Almacen<MyCustomObject>` para gestionar una lista de elementos que se muestran en un `LazyColumn`.

## Ejercicio 2: Procesador de Datos Numéricos (Restricciones de Tipo)

Necesitáis una función que pueda calcular el promedio de una lista de números, ya sean enteros, dobles, etc.

**Tarea:**

1.  Crea una función genérica llamada `calcularPromedio<T>` que acepte una `List<T>` como parámetro.
2.  Aplica una restricción de tipo para asegurar que `T` sea un subtipo de `Number`. Esto os permitirá llamar a métodos como `toDouble()` en los elementos de la lista.
3.  La función debe devolver el promedio de los números como un `Double`. Si la lista está vacía, debe devolver `0.0`.
4.  En la función `main`, prueba la función `calcularPromedio` con:
    - Una `List<Int>`.
    - Una `List<Double>`.
    - Intentad pasar una `List<String>` y observad el error de compilación.

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 2: Restricciones de Tipo ---")

    val enteros = listOf(10, 20, 30)
    val dobles = listOf(5.5, 2.0, 10.0)
    // val strings = listOf("10", "20")

    println("Promedio Enteros: ${calcularPromedio(enteros)}")
    println("Promedio Dobles:  ${calcularPromedio(dobles)}")
    
    // calcularPromedio(strings) // Error: Type argument is not within its bounds.
    println("------------------------------------------\n")
}

// T : Number restringe T a subclases de Number (Int, Double, Float, Long...)
fun <T : Number> calcularPromedio(lista: List<T>): Double {
    if (lista.isEmpty()) return 0.0
    
    // .map { it.toDouble() } convierte cada número a Double para sumar
    val suma = lista.sumOf { it.toDouble() }
    return suma / lista.size
}
```
</details>

**Pista:** La restricción `T : Number` es clave para poder realizar operaciones matemáticas en `T`.

## Ejercicio 3: Intercambiador Universal (Funciones Genéricas)

Diseñad una función que pueda intercambiar dos elementos en cualquier tipo de `Array`.

**Tarea:**

1.  Crea una función genérica `intercambiarElementos<T>(array: Array<T>, index1: Int, index2: Int): Unit`.
2.  La función debe intercambiar los elementos en las posiciones `index1` y `index2` dentro del `array`.
3.  Asegúrate de incluir una validación para los índices para evitar `IndexOutOfBoundsException`. Si los índices no son válidos, imprime un mensaje de error y no hagas nada.
4.  En la función `main`, prueba la función `intercambiarElementos` con:
    - Un `Array<String>`.
    - Un `Array<Boolean>`.
    - Un `Array<Char>`.
    - Prueba también con índices inválidos.

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 3: Intercambiador Universal ---")

    val palabras = arrayOf("Hola", "Mundo", "Kotlin")
    println("Antes: ${palabras.contentToString()}")
    intercambiarElementos(palabras, 0, 2)
    println("Después (0<->2): ${palabras.contentToString()}")

    val numeros = arrayOf(1, 2, 3, 4)
    intercambiarElementos(numeros, 0, 10) // Índice inválido
   
    println("-------------------------------------------\n")
}

// Función genérica sobre un Array de T
fun <T> intercambiarElementos(array: Array<T>, index1: Int, index2: Int) {
    if (index1 in array.indices && index2 in array.indices) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    } else {
        println("  [Error] Índices fuera de rango: $index1, $index2")
    }
}
```
</details>

**Pista:** Las funciones genéricas son perfectas para operaciones de utilidad que son aplicables a muchos tipos de datos.

## Ejercicio 4: Filtro de Colecciones con Predicado (Funciones de Extensión Genéricas)

Queréis una forma general de filtrar elementos de cualquier colección basándose en una condición.

**Tarea:**

1.  Crea una función de extensión genérica para `List<T>` llamada `filtrarPorCondicion<T>(predicado: (T) -> Boolean): List<T>`.
2.  La función debe devolver una nueva lista que contenga solo los elementos de la lista original para los cuales el `predicado` (una función lambda) devuelve `true`.
3.  En la función `main`, prueba `filtrarPorCondicion` con:
    - Una `List<Int>` para filtrar números pares.
    - Una `List<String>` para filtrar cadenas que empiecen por "A".
    - Una `List<Persona>` (definid una `data class Persona(val nombre: String, val edad: Int)`) para filtrar personas mayores de 30 años.

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 4: Filtro Genérico ---")

    val numeros = listOf(1, 2, 3, 4, 5, 6)
    val pares = numeros.filtrarPorCondicion { it % 2 == 0 }
    println("Pares: $pares")

    val nombres = listOf("Ana", "Pedro", "Antonio", "Beatriz")
    val empiezanPorA = nombres.filtrarPorCondicion { it.startsWith("A") }
    println("Empiezan por A: $empiezanPorA")
    
    val personas = listOf(Persona("Juan", 25), Persona("Maria", 34))
    val mayores30 = personas.filtrarPorCondicion { it.edad > 30 }
    println("Mayores de 30: $mayores30")

    println("------------------------------------\n")
}

data class Persona(val nombre: String, val edad: Int)

// Función de extensión sobre List<T>
fun <T> List<T>.filtrarPorCondicion(predicado: (T) -> Boolean): List<T> {
    val resultado = mutableListOf<T>()
    for (item in this) {
        if (predicado(item)) {
            resultado.add(item)
        }
    }
    return resultado
    // O simplemente: return this.filter(predicado)
}
```
</details>

**Pista:** Esta es una forma muy común de usar genéricos en Kotlin y se parece mucho a las funciones de orden superior en las colecciones estándar.

## Ejercicio 5: Proveedor y Consumidor (Varianza - `in` y `out`)

Este ejercicio explora la varianza, que es un concepto más avanzado pero crucial para entender cómo funcionan las interfaces genéricas de Kotlin.

**Tarea:**

1.  Define una clase base `Animal` y dos subclases: `Perro` y `Gato`.
2.  Define una interfaz `abstract` genérica `Proveedor<out T>` con un método `obtener(): T`.
    - Crea una implementación concreta `ProveedorPerros` que devuelva un `Perro`.
3.  Define una interfaz `abstract` genérica `Consumidor<in T>` con un método `procesar(item: T)`.
    - Crea una implementación concreta `ConsumidorAnimales` que procese cualquier `Animal`.
4.  En la función `main`, demuestra la varianza:
    - Para `Proveedor`: Declara una variable `val proveedorAnimal: Proveedor<Animal>` y asígnale una instancia de `ProveedorPerros`. Llama a `obtener()` y verifica que devuelve un `Animal` (que de hecho es un `Perro`). Esto es posible gracias a `out`.
    - Para `Consumidor`: Declara una variable `val consumidorPerros: Consumidor<Perro>` y asígnale una instancia de `ConsumidorAnimales`. Llama a `procesar()` con un `Perro`. Esto es posible gracias a `in`.
    - Intenta hacer las asignaciones inversas (ej. `Proveedor<Perro> = Proveedor<Animal>`) y observa los errores de compilación.

<details>
<summary><b>Solución Ejercicio 5</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 5: Varianza (in/out) ---")

    // --- COVARIANZA (out) ---
    // Un Proveedor<Perro> ES UN Proveedor<Animal> porque solo devuelve datos
    val proveedorPerros = ProveedorPerros()
    val proveedorAnimal: Proveedor<Animal> = proveedorPerros 
    
    val animalObtenido: Animal = proveedorAnimal.obtener()
    println("Animal obtenido: ${animalObtenido.javaClass.simpleName}")


    // --- CONTRAVARIANZA (in) ---
    // Un Consumidor<Animal> ES UN Consumidor<Perro> porque puede aceptar Perros
    val consumidorAnimales = ConsumidorAnimales()
    val consumidorPerros: Consumidor<Perro> = consumidorAnimales
    
    consumidorPerros.procesar(Perro()) // Funciona
    
    // consumidorPerros.procesar(Gato()) // Error: accepta Perro, Gato no es Perro
    
    println("--------------------------------------\n")
}

// Jerarquía de Clases
open class Animal
class Perro : Animal()
class Gato : Animal()

// Interfaces Genéricas
// out T: Covariante (Produce T). Puede asignarse a un tipo padre (Provider<Perro> -> Provider<Animal>)
interface Proveedor<out T> {
    fun obtener(): T
}

class ProveedorPerros : Proveedor<Perro> {
    override fun obtener() = Perro()
}

// in T: Contravariante (Consume T). Puede asignarse a un tipo hijo (Consumer<Animal> -> Consumer<Perro>)
interface Consumidor<in T> {
    fun procesar(item: T)
}

class ConsumidorAnimales : Consumidor<Animal> {
    override fun procesar(item: Animal) {
        println("Procesando animal: ${item.javaClass.simpleName}")
    }
}
```
</details>

**Pista:** Pensad en `out` como "solo producir" y `in` como "solo consumir". Si una clase genérica solo produce elementos de tipo `T`, puede ser `out`. Si solo consume, puede ser `in`.

---

¡Estos ejercicios os ayudarán a dominar los genéricos! Recordad que la práctica es clave. Si os quedáis atascados, no dudéis en revisar los ejemplos resueltos o las explicaciones. ¡A programar!
