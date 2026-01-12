# Ejercicios: Scope Functions (Funciones de Alcance)

En esta serie de ejercicios practicaremos las *Scope Functions* (`let`, `run`, `with`, `apply`, `also`). Estas funciones son esenciales en Kotlin para escribir un código más limpio, conciso y expresivo, especialmente cuando trabajamos con objetos configurables o lógica encadenada.

> Video explicativo: [Guía Completa de Kotlin Scope Functions: let, run, with, apply y also](https://youtu.be/CGxigf122Po?t=3356)

## Ejercicio 1: Procesamiento de Usuario (let)

El uso más común de `let` es la seguridad ante nulos y la transformación de datos.

**Tarea:**

1.  Crea una variable nulable llamada `nombreUsuario: String?`.
2.  Usa `let` para realizar las siguientes acciones solo si `nombreUsuario` no es nulo:
    - Imprimir el nombre en mayúsculas.
    - Contar los caracteres del nombre.
    - Devolver un mensaje que diga: "El usuario [NOMBRE] tiene [X] letras".
3.  Si es nulo, el resultado debe ser "Usuario no identificado".
4.  Imprime el resultado final.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 1: Seguridad con 'let' ---")
    val nombre1: String? = "Alejandro"
    val nombre2: String? = null

    println("Resultado 1: ${procesarNombre(nombre1)}")
    println("Resultado 2: ${procesarNombre(nombre2)}")
}
```

**Pistas:**

- El operador de llamada segura `?.let { ... }` es tu mejor aliado aquí.
- Puedes usar el operador Elvis `?:` para el caso en que sea nulo.

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
fun main() {
    println("--- EJERCICIO 1: Seguridad con 'let' ---")
    val nombre1: String? = "Alejandro"
    val nombre2: String? = null

    println("Resultado 1: ${procesarNombre(nombre1)}")
    println("Resultado 2: ${procesarNombre(nombre2)}")
}

fun procesarNombre(nombre: String?): String {
    return nombre?.let {
        val mayusculas = it.uppercase()
        val longitud = it.length
        "El usuario $mayusculas tiene $longitud letras"
    } ?: "Usuario no identificado"
}
```
</details>

## Ejercicio 2: Configuración de un Perfil de Estudiante (apply)

`apply` se utiliza principalmente para configurar las propiedades de un objeto y devolver el objeto configurado.

**Tarea:**

1.  Dada la clase `Estudiante(var nombre: String, var edad: Int)`, añade las propiedades `curso: String` y `notaMedia: Double` (con valores por defecto).
2.  En el `main`, crea un estudiante y usa `apply` para configurar su curso y su nota media en un solo bloque.
3.  Imprime el objeto final para verificar los cambios.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 2: Configuración con 'apply' ---")
    val estudiante = Estudiante("Lucía", 20).apply {
        // Tu código aquí
    }
    
    println("Ficha del estudiante: $estudiante")
}
```

**Pistas:**

- Dentro de `apply`, el contexto es `this`, por lo que puedes acceder directamente a las propiedades.
- `apply` devuelve el propio objeto, lo que permite asignarlo directamente a la variable.

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
data class Estudiante(
    var nombre: String,
    var edad: Int,
    var curso: String = "Pendiente",
    var notaMedia: Double = 0.0
)

fun main() {
    println("--- EJERCICIO 2: Configuración con 'apply' ---")
    val estudiante = Estudiante("Lucía", 20).apply {
        curso = "2º DAM"
        notaMedia = 8.75
        println("  [Log] Configurando perfil de $nombre...")
    }
    
    println("Ficha del estudiante: $estudiante")
}
```
</details>

## Ejercicio 3: Registro de Operaciones Bancarias (also)

`also` es ideal para realizar acciones secundarias (como imprimir logs o validar estados) sin alterar el objeto ni el flujo de la cadena.

**Tarea:**

1.  Crea una clase `CuentaBancaria(val titular: String, var saldo: Double)`.
2.  Implementa una función `ingresarDinero(cantidad: Double)`.
3.  Dentro de esa función, aumenta el saldo y usa `also` para mostrar un mensaje de log: "LOG: Ingreso de [CANTIDAD]. Nuevo saldo: [SALDO]".
4.  En el `main`, realiza varios ingresos encadenados si es posible.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 3: Logs con 'also' ---")
    val miCuenta = CuentaBancaria("Juan Pérez", 1000.0)
    
    miCuenta.ingresarDinero(500.0)
    miCuenta.ingresarDinero(200.0)
}
```

**Pistas:**

- `also` recibe el objeto como `it` y devuelve el mismo objeto.
- Es perfecto para "echar un vistazo" al objeto en mitad de una operación.

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
class CuentaBancaria(val titular: String, var saldo: Double) {
    fun ingresarDinero(cantidad: Double) {
        saldo += cantidad
        // Usamos also para el efecto secundario de loguear
        this.also { 
            println("  [LOG] Ingreso de $cantidad€. Nuevo saldo: ${it.saldo}€") 
        }
    }
}

fun main() {
    println("--- EJERCICIO 3: Logs con 'also' ---")
    val miCuenta = CuentaBancaria("Juan Pérez", 1000.0)
    
    miCuenta.ingresarDinero(500.0)
    miCuenta.ingresarDinero(200.0)
}
```
</details>

## Ejercicio 4: Análisis de Pedido (run y with)

`run` y `with` se usan para ejecutar un bloque de código y devolver un resultado basado en el objeto de contexto (`this`).

**Tarea:**

1.  Tenemos una clase `Pedido(val id: Int, val productos: List<Double>)` (donde la lista son los precios).
2.  Usa `with(pedido)` para calcular el total del pedido y devolver un string informativo.
3.  Usa `run` sobre un pedido que puede ser nulo para aplicar un descuento si el total supera los 100€ y devolver el nuevo total.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 4: Cálculo con 'run' y 'with' ---")
    val pedidoActivo = Pedido(101, listOf(50.0, 30.0, 40.0))
    val pedidoNulo: Pedido? = null

    // 1. Usar with para el resumen
    val resumen = with(pedidoActivo) {
        "Pedido #$id con ${productos.size} productos."
    }
    println(resumen)

    // 2. Usar run para el descuento
    val totalConDescuento = pedidoActivo.run {
        val total = productos.sum()
        if (total > 100) total * 0.9 else total
    }
    println("Total final (con posible descuento): $totalConDescuento")
}
```

**Pistas:**

- `with(obj) { ... }` es útil cuando el objeto no es nulo.
- `obj?.run { ... }` es excelente para objetos que pueden ser nulos y requieren lógica interna compleja.

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
data class Pedido(val id: Int, val productos: List<Double>)

fun main() {
    println("--- EJERCICIO 4: Cálculo con 'run' y 'with' ---")
    val pedidoActivo = Pedido(101, listOf(50.0, 30.0, 40.0))

    // Uso de with para extraer información
    val resumen = with(pedidoActivo) {
        val totalItems = productos.size
        "Pedido #$id con $totalItems productos."
    }
    println(resumen)

    // Uso de run para lógica de negocio y retorno de valor
    val totalConDescuento = pedidoActivo.run {
        val totalOriginal = productos.sum()
        if (totalOriginal > 100) {
            println("  [Promo] Aplicando 10% de descuento...")
            totalOriginal * 0.9
        } else {
            totalOriginal
        }
    }
    println("Total final: $totalConDescuento€")
}
```
</details>

## Ejercicio 5: Simulando un UiState de Compose (Encadenamiento)

En Jetpack Compose es muy común encadenar Scope Functions para transformar estados de la interfaz.

**Tarea:**

1.  Crea un `data class UiState(val nombre: String, val cargando: Boolean, val error: String?)`.
2.  Simula un proceso donde:
    - Creas un estado inicial.
    - Usas `copy()` junto con `apply` para cambiar `cargando` a `false`.
    - Usas `also` para imprimir un log del cambio de estado.
    - Usas `let` para devolver un mensaje amigable al usuario dependiendo de si hay error o no.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 5: Flujo de Estado (UiState) ---")
    val estadoActual = UiState("Pantalla Login", true, null)

    val mensajeFinal = estadoActual.copy().apply {
        // 1. Actualizar cargando a false
    }.also {
        // 2. Loguear el nuevo estado
    }.let {
        // 3. Devolver un string "Listo para usar" o "Error: [MSG]"
    }

    println("Mensaje para el usuario: $mensajeFinal")
}
```

**Pistas:**

- Recuerda el orden de retorno: `apply` y `also` devuelven el objeto; `let` y `run` devuelven el resultado de la lambda.

<details>
<summary><b>Solución Ejercicio 5</b></summary>

```kotlin
data class UiState(
    val nombrePantalla: String, 
    val isLoading: Boolean, 
    val error: String?
)

fun main() {
    println("--- EJERCICIO 5: Flujo de Estado (UiState) ---")
    val estadoActual = UiState("Home", true, null)

    val mensajeFinal = estadoActual.copy(isLoading = false).apply {
        // Configuraciones adicionales si fueran necesarias
        println("  [UI] Finalizando carga de $nombrePantalla...")
    }.also {
        println("  [LOG] Estado actualizado: $it")
    }.let {
        if (it.error != null) "Error al cargar: ${it.error}" 
        else "Pantalla '${it.nombrePantalla}' cargada correctamente."
    }

    println("Mensaje para el usuario: $mensajeFinal")
}
```
</details>

---

**¡Excelente trabajo!** Has practicado las 5 funciones de alcance principales. Dominarlas te permitirá escribir código mucho más idiomático y aprovechar toda la potencia de la sintaxis de Kotlin.
