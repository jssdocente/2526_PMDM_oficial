### **Ejercicios: Funciones Lambda en Kotlin**

#### **Ejercicio 1: Lambda como Último Parámetro (Trailing Lambda)**

**Objetivo:** Practicar la definición de funciones que aceptan una lambda como último parámetro y su invocación usando la sintaxis de `trailing lambda`.

**Descripción:**
Crea una función llamada `ejecutarConRetraso` que acepte un `Long` (milisegundos de retraso) y una lambda sin parámetros que no devuelva nada (`() -> Unit`). La función debe imprimir un mensaje indicando el retraso, "simular" el retraso (puedes usar `Thread.sleep()`), y luego ejecutar la lambda.

**Instrucciones:**
1.  Define la función `ejecutarConRetraso`.
2.  Llama a `ejecutarConRetraso` dos veces:
    *   La primera vez, usa la sintaxis estándar de pasar la lambda dentro de los paréntesis.
    *   La segunda vez, usa la sintaxis de `trailing lambda`.
3.  Dentro de las lambdas, simplemente imprime un mensaje indicando que la acción se ha ejecutado.

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 1: Trailing Lambda ---")
    // Tu código aquí
    println("-------------------------------------\n")
}

// Define la función ejecutarConRetraso aquí
fun ejecutarConRetraso(retrasoMs: Long, accion: () -> Unit) {
    println("Esperando $retrasoMs ms antes de ejecutar la acción...")
    Thread.sleep(retrasoMs) // Simulación de retraso
    accion()
    println("Acción con retraso finalizada.")
}
```

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 1: Trailing Lambda ---")

    // Llamada con lambda estándar (dentro de paréntesis)
    ejecutarConRetraso(1000, {
        println("  -> ¡Acción 1 ejecutada después del retraso (sintaxis estándar)!")
    })

    println("\n")

    // Llamada con trailing lambda (fuera de paréntesis)
    ejecutarConRetraso(500) {
        println("  -> ¡Acción 2 ejecutada después del retraso (trailing lambda)!")
    }
    println("-------------------------------------\n")
}

// Define la función ejecutarConRetraso aquí
fun ejecutarConRetraso(retrasoMs: Long, accion: () -> Unit) {
    println("Esperando $retrasoMs ms antes de ejecutar la acción...")
    Thread.sleep(retrasoMs) // Simulación de retraso
    accion()
    println("Acción con retraso finalizada.")
}
```
</details>

---

#### **Ejercicio 2: El parámetro implícito `it`**

**Objetivo:** Practicar el uso del parámetro `it` cuando una lambda tiene un único argumento.

**Descripción:**
Crea una función llamada `procesarElemento` que reciba un `String` (el elemento a procesar) y una lambda que tome un `String` y devuelva un `String`. La función `procesarElemento` debe aplicar la lambda al elemento y luego imprimir el resultado.

**Instrucciones:**
1.  Define la función `procesarElemento`.
2.  Llama a `procesarElemento` dos veces:
    *   La primera vez, usando la sintaxis de `trailing lambda` y declarando explícitamente el parámetro de la lambda (ej. `elemento -> elemento.uppercase()`).
    *   La segunda vez, usando la sintaxis de `trailing lambda` y el parámetro implícito `it`.
3.  Las lambdas deben realizar alguna transformación simple, como convertir a mayúsculas o añadir un prefijo.

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 2: Parámetro 'it' ---")
    // Tu código aquí
    println("-------------------------------------\n")
}

// Define la función procesarElemento aquí
fun procesarElemento(elemento: String, transformacion: (String) -> String) {
    val resultado = transformacion(elemento)
    println("Original: '$elemento' -> Procesado: '$resultado'")
}
```

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 2: Parámetro 'it' ---")

    // Usando trailing lambda con parámetro explícito
    procesarElemento("kotlin") { texto ->
        texto.uppercase() + "!"
    }

    // Usando trailing lambda con 'it'
    procesarElemento("compose") {
        it + " is fun!" // 'it' se refiere al parámetro String
    }
    println("-------------------------------------\n")
}

// Define la función procesarElemento aquí
fun procesarElemento(elemento: String, transformacion: (String) -> String) {
    val resultado = transformacion(elemento)
    println("Original: '$elemento' -> Procesado: '$resultado'")
}
```
</details>

---

#### **Ejercicio 3: Lambdas en Colecciones (Reforzando `trailing lambda` e `it`)**

**Objetivo:** Reforzar el uso de `trailing lambda` e `it` con las funciones de extensión de colecciones de Kotlin.

**Descripción:**
Tienes una lista de números. Realiza varias operaciones sobre ella usando las funciones de colección de Kotlin.

**Instrucciones:**
1.  Define una lista inmutable de números enteros.
2.  Utiliza `forEach` para imprimir cada número, añadiendo un prefijo. Usa `trailing lambda` e `it`.
3.  Utiliza `filter` para obtener solo los números pares. Usa `trailing lambda` e `it`.
4.  Utiliza `map` para duplicar cada número. Usa `trailing lambda` e `it`.

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 3: Lambdas en Colecciones ---")
    val numeros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Lista original: $numeros")

    // 1. Imprimir cada número con forEach
    println("\nImprimiendo cada número:")
    // Tu código aquí

    // 2. Filtrar números pares
    println("\nNúmeros pares:")
    // Tu código aquí

    // 3. Duplicar cada número
    println("\nNúmeros duplicados:")
    // Tu código aquí

    println("-------------------------------------------\n")
}
```

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 3: Lambdas en Colecciones ---")
    val numeros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Lista original: $numeros")

    // 1. Imprimir cada número con forEach
    println("\nImprimiendo cada número:")
    numeros.forEach {
        println("  Número: $it")
    }

    // 2. Filtrar números pares
    println("\nNúmeros pares:")
    val pares = numeros.filter { it % 2 == 0 }
    println("  $pares")

    // 3. Duplicar cada número
    println("\nNúmeros duplicados:")
    val duplicados = numeros.map { it * 2 }
    println("  $duplicados")

    println("-------------------------------------------\n")
}
```
</details>

---

#### **Ejercicio 4: Función con Múltiples Parámetros y Trailing Lambda**

**Objetivo:** Entender cómo se comporta la `trailing lambda` cuando la función tiene otros parámetros antes de la lambda.

**Descripción:**
Crea una función llamada `configurarTarea` que acepte un `String` para el nombre de la tarea, un `Int` para la prioridad, y una lambda sin parámetros que no devuelva nada (`() -> Unit`) para la acción a realizar.

**Instrucciones:**
1.  Define la función `configurarTarea`.
2.  Llama a `configurarTarea` dos veces usando la sintaxis de `trailing lambda`.
    *   La primera vez, con parámetros nombrados para `nombre` y `prioridad`.
    *   La segunda vez, sin parámetros nombrados (Kotlin infiere el orden).
3.  Dentro de las lambdas, imprime un mensaje indicando la acción que se está "configurando".

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 4: Múltiples Parámetros y Trailing Lambda ---")
    // Tu código aquí
    println("-----------------------------------------------------------\n")
}

// Define la función configurarTarea aquí
fun configurarTarea(nombre: String, prioridad: Int, accion: () -> Unit) {
    println("Configurando tarea '$nombre' con prioridad $prioridad...")
    accion()
    println("Tarea '$nombre' configurada.")
}
```

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 4: Múltiples Parámetros y Trailing Lambda ---")

    // Llamada con parámetros nombrados y trailing lambda
    configurarTarea(nombre = "Comprar víveres", prioridad = 1) {
        println("  -> Revisando lista de la compra y planificando ruta.")
    }

    println("\n")

    // Llamada sin parámetros nombrados (Kotlin infiere el orden) y trailing lambda
    configurarTarea("Programar app Compose", 9) {
        println("  -> Abriendo Android Studio y pensando en la UI.")
    }

    println("-----------------------------------------------------------\n")
}

// Define la función configurarTarea aquí
fun configurarTarea(nombre: String, prioridad: Int, accion: () -> Unit) {
    println("Configurando tarea '$nombre' con prioridad $prioridad...")
    accion()
    println("Tarea '$nombre' configurada.")
}
```
</details>

---

### **Reflexión Final:**

¡Muy bien hecho! Si habéis completado estos ejercicios, habéis reforzado puntos clave:

*   **`Trailing Lambda`**: La comodidad y legibilidad de colocar la lambda fuera de los paréntesis cuando es el último argumento. Esto es CRUCIAL para Compose, donde los bloques de UI (`Column { ... }`, `Button { ... }`) usan esta sintaxis.
*   **`it`**: La elegancia de usar `it` para el único parámetro de una lambda, lo que hace el código más compacto. Lo veréis en todas las operaciones de colecciones y muchas APIs de Compose.

Estas dos características, combinadas, son las que dan al código Kotlin ese aspecto limpio y casi de "lenguaje de dominio" que tanto se valora, especialmente en el contexto de Compose. ¡Seguid practicando!