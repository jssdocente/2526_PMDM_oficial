# UT2. Ejercicios Kotlin Avanzados
---

## 🔒 15. Clases Selladas (Sealed Classes)

Las clases selladas son la versión mejorada de los `enum`. Representan jerarquías de clases restringidas. Todos los subtipos de una `sealed class` deben estar definidos en el mismo fichero.

Su gran ventaja es que, al usarlas con `when`, el compilador puede verificar que has cubierto todos los casos posibles, eliminando la necesidad de una rama `else`. Son perfectas para representar estados o resultados de una operación.

```kotlin
sealed class Resultado {
    data class Exito(val datos: String) : Resultado()
    data class Error(val mensaje: String) : Resultado()
    object Cargando : Resultado()
}

fun manejarResultado(resultado: Resultado) {
    when (resultado) {
        is Resultado.Exito -> println("Éxito: ${resultado.datos}")
        is Resultado.Error -> println("Error: ${resultado.mensaje}")
        Resultado.Cargando -> println("Cargando...")
    } // No se necesita 'else'
}
```

🔗 **Documentación Oficial:** [Sealed Classes](https://kotlinlang.org/docs/sealed-classes.html)

---

#### 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.sealed`
#### 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Jerarquía Simple**
    *   Crea una `sealed class` `Forma` con tres subtipos: `Circulo(val radio: Double)`, `Cuadrado(val lado: Double)` y `Rectangulo(val ancho: Double, val alto: Double)`.

2.  **Ejercicio 2: `when` Exhaustivo**
    *   Crea una función `calcularArea(forma: Forma)` que use una expresión `when` para calcular y devolver el área según el tipo de forma. Observa que no necesitas una rama `else`.

3.  **Ejercicio 3: Estado de UI**
    *   Modela el estado de una pantalla con una `sealed class` `EstadoUI` que tenga los subtipos: `Cargando`, `Exito(val datos: List<String>)` y `Error(val mensaje: String)`.

---

## ✨ 16. Scope Functions (`let`, `run`, `with`, `apply`, `also`)

Las funciones de scope son una de las características más idiomáticas de Kotlin. Permiten ejecutar un bloque de código en el contexto de un objeto. Las principales son:

| Función | Objeto de Contexto | Valor de Retorno | Uso Común                                    |
| :------ | :----------------- | :--------------- | :------------------------------------------- |
| `let`     | `it`               | Resultado lambda | Ejecutar código en un objeto no nulo.        |
| `run`     | `this`             | Resultado lambda | Configurar un objeto y calcular un resultado.|
| `with`    | `this`             | Resultado lambda | Operar sobre un objeto sin llamarlo.         |
| `apply`   | `this`             | Objeto           | Configuración de un objeto (Builder).        |
| `also`    | `it`               | Objeto           | Acciones adicionales sobre un objeto (logging).|

🔗 **Documentación Oficial:** [Scope Functions](https://kotlinlang.org/docs/scope-functions.html)

---

#### 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.scope`
#### 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: `let` para Nulos**
    *   Crea una variable `nombre: String? = "Juan"`. Usa `let` para imprimir su longitud solo si no es nulo.

2.  **Ejercicio 2: `apply` para Configuración**
    *   Crea una clase `TextView` con propiedades `var text: String` y `var textSize: Int`.
    *   Crea una instancia y usa `apply` para configurar sus propiedades en un solo bloque.

3.  **Ejercicio 3: `run` para Calcular**
    *   Dada una instancia de `TextView`, usa `run` para devolver un `String` que describa sus propiedades, como `"Texto: [texto], Tamaño: [tamaño]"`.

4.  **Ejercicio 4: `with` para Agrupar Llamadas**
    *   Crea una lista mutable de números. Usa `with` para realizar varias operaciones sobre ella (añadir elementos, eliminar, etc.) sin tener que repetir el nombre de la variable de la lista.

5.  **Ejercicio 5: `also` para Logging o Acciones Secundarias**
    *   Crea una lista mutable, usa `also` después de añadir un elemento para imprimir un log como "Elemento añadido: [elemento]". La función debe devolver la lista modificada.

6.  **Ejercicio 6: Combinación**
    *   Dada una variable `usuario: Usuario?`, usa `let` para comprobar si no es nulo y, dentro, usa `apply` para modificar alguna de sus propiedades.

---

## ⏳ 17. Corrutinas (Nivel Básico)

Las corrutinas son la forma moderna y simplificada de manejar la asincronía en Kotlin. Permiten escribir código asíncrono como si fuera síncrono, evitando el "callback hell".

*   `launch`: Inicia una corrutina que no devuelve un resultado ("fire and forget").
*   `async`: Inicia una corrutina que devuelve un resultado a través de un `Deferred`.
*   `await()`: Se usa en un `Deferred` para esperar el resultado.
*   `delay()`: Pausa la corrutina sin bloquear el hilo.
*   `suspend`: Las funciones que usan `delay` o llaman a otras funciones de suspensión deben marcarse con `suspend`.

🔗 **Documentación Oficial:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

---

#### 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.corrutinas`
#### 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

*Nota: Para ejecutar corrutinas en un fichero simple, necesitarás un `runBlocking` o `GlobalScope` (este último no recomendado en producción).*

### Ejercicios Propuestos:

1.  **Ejercicio 1: `launch` Básico**
    *   En un bloque `runBlocking`, usa `launch` para iniciar una corrutina que espere 1 segundo (`delay(1000L)`) y luego imprima "¡Hola desde la corrutina!".

2.  **Ejercicio 2: Múltiples Corrutinas**
    *   Lanza dos corrutinas. La primera espera 2 segundos y la segunda 1 segundo. Observa en qué orden imprimen sus mensajes.

3.  **Ejercicio 3: `async` y `await`**
    *   Usa `async` para crear una corrutina que simule la obtención de datos de red (con un `delay`) y devuelva un `String`.
    *   Usa `await()` para esperar el resultado e imprimirlo.

4.  **Ejercicio 4: Funciones `suspend`**
    *   Crea una `suspend fun obtenerUsuario(id: Int): String` que simule una búsqueda en base de datos (con `delay`) y devuelva un nombre de usuario.
    *   Llama a esta función desde una corrutina.

5.  **Ejercicio 5 (Más Avanzado): Concurrencia con `async`**
    *   Necesitas obtener dos datos de forma concurrente: el nombre de un usuario y sus notificaciones.
    *   Usa dos `async` para lanzar ambas "peticiones" a la vez.
    *   Luego, usa `await()` en ambos `Deferred` para esperar a que los dos terminen. Mide el tiempo total; debería ser aproximadamente el de la operación más larga, no la suma de ambas.
