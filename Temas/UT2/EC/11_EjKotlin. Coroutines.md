# Ejercicios: Kotlin Coroutines (Corrutinas)

Las corrutinas son una de las características más potentes de Kotlin, permitiéndonos escribir código asíncrono de forma secuencial y eficiente. En estos ejercicios practicaremos la suspensión, el paralelismo y la gestión de contextos.

> Video explicativo: [Guía Completa de Kotlin Coroutines: Introducción, Suspensión y Paralelismo](https://www.youtube.com/embed/vGgB4wBjM-c)

## Ejercicio 1: Mi Primera Suspensión (delay)

El concepto fundamental de las corrutinas es la capacidad de pausar la ejecución sin bloquear el hilo.

**Tarea:**

1.  Crea una `suspend fun` llamada `saludarConRetraso(nombre: String, tiempo: Long)`.
2.  La función debe:
    - Imprimir "Hola, $nombre. Preparando saludo..."
    - Esperar el tiempo indicado usando `delay()`.
    - Imprimir "¡Saludo para $nombre listo tras $tiempo ms!".
3.  En la función `main`, llama a esta función dos veces de forma secuencial con diferentes nombres y tiempos.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 1: Suspensión Básica ---")
    val inicio = System.currentTimeMillis()

    saludarConRetraso("Juan", 1000)
    saludarConRetraso("Ana", 500)

    val fin = System.currentTimeMillis()
    println("Tiempo total transcurrido: ${fin - inicio} ms")
    println("--------------------------------------\n")
}
```

**Pistas:**

- `delay()` es una función de suspensión, por lo que solo puede llamarse desde otra función `suspend` o dentro de una corrutina.
- `runBlocking` se usa en el `main` para poder llamar a funciones `suspend` y esperar a que terminen.

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 1: Suspensión Básica ---")
    val inicio = System.currentTimeMillis()

    saludarConRetraso("Juan", 1000)
    saludarConRetraso("Ana", 500)

    val fin = System.currentTimeMillis()
    println("Tiempo total transcurrido: ${fin - inicio} ms")
    println("--------------------------------------\n")
}

suspend fun saludarConRetraso(nombre: String, tiempo: Long) {
    println("  [${Thread.currentThread().name}] -> Hola, $nombre. Preparando saludo...")
    delay(tiempo)
    println("  [${Thread.currentThread().name}] -> ¡Saludo para $nombre listo tras $tiempo ms!")
}
```
</details>

## Ejercicio 2: El Desayuno Paralelo (async/await)

Si tenemos tareas que no dependen entre sí, ejecutarlas de forma secuencial es un desperdicio de tiempo. Usaremos `async` para ejecutarlas en paralelo.

**Tarea:**

1.  Crea dos funciones de suspensión: `prepararCafe()` (tarda 1500ms y devuelve "Café recién hecho") y `tostarPan()` (tarda 1000ms y devuelve "Tostada crujiente").
2.  En el `main`, lanza ambas tareas para que se ejecuten **al mismo tiempo**.
3.  Espera a que ambas terminen, combina sus resultados en un mensaje de "Desayuno listo: [café] y [pan]" e imprímelo.
4.  Verifica que el tiempo total sea aproximadamente el de la tarea más larga (1500ms), no la suma de ambas.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 2: Desayuno Paralelo ---")
    val inicio = System.currentTimeMillis()

    // Tarea: Lanzar café y pan en paralelo aquí
    
    // Tarea: Esperar resultados y mostrar desayuno

    val fin = System.currentTimeMillis()
    println("Tiempo total: ${fin - inicio} ms")
    println("--------------------------------------\n")
}
```

**Pistas:**

- `async { ... }` devuelve un objeto `Deferred<T>`.
- Para obtener el valor real de un `Deferred`, debes llamar a `.await()`.
- Si llamas a `.await()` inmediatamente después de `async`, volverás a tener una ejecución secuencial. ¡Lanza todos los `async` primero!

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 2: Desayuno Paralelo ---")
    val inicio = System.currentTimeMillis()

    // Lanzamos las tareas en paralelo
    val deferredCafe = async { prepararCafe() }
    val deferredPan = async { tostarPan() }

    println("  (Mientras se hace el desayuno, puedo poner la mesa...)")
    delay(500)

    // Obtenemos los resultados
    val cafe = deferredCafe.await()
    val pan = deferredPan.await()

    println("  Resultado: Desayuno listo -> $cafe y $pan")

    val fin = System.currentTimeMillis()
    println("Tiempo total: ${fin - inicio} ms")
    println("--------------------------------------\n")
}

suspend fun prepararCafe(): String {
    delay(1500)
    return "Café recién hecho"
}

suspend fun tostarPan(): String {
    delay(1000)
    return "Tostada crujiente"
}
```
</details>

## Ejercicio 3: Cambio de Hilo (withContext)

No todas las tareas deben ejecutarse en el mismo sitio. Las peticiones a base de datos o red deben ir a hilos optimizados para Entrada/Salida (IO).

**Tarea:**

1.  Crea una función `descargarImagen(url: String)` que simule una descarga.
2.  Dentro de la función, usa `withContext(Dispatchers.IO)` para realizar la "descarga" (un `delay` de 1000ms).
3.  Imprime el nombre del hilo actual antes de entrar en `withContext` y dentro de él para ver el cambio.
4.  Devuelve un string "Imagen de $url descargada".

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 3: Cambio de Contexto ---")
    
    val resultado = descargarImagen("https://mi-servidor.com/foto.jpg")
    println("Resultado final: $resultado")
    
    println("---------------------------------------\n")
}
```

**Pistas:**

- `Dispatchers.Main` es para UI, `Dispatchers.IO` para red/disco, y `Dispatchers.Default` para cálculos intensivos de CPU.
- `Thread.currentThread().name` te dice en qué hilo estás.

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 3: Cambio de Contexto ---")
    
    println("  [${Thread.currentThread().name}] -> Llamando a descargarImagen...")
    val resultado = descargarImagen("https://mi-servidor.com/foto.jpg")
    println("  [${Thread.currentThread().name}] -> Resultado final: $resultado")
    
    println("---------------------------------------\n")
}

suspend fun descargarImagen(url: String): String {
    // Forzamos el cambio a hilos de IO
    return withContext(Dispatchers.IO) {
        println("  [${Thread.currentThread().name}] -> Descargando bit a bit desde $url...")
        delay(1000)
        "Imagen de $url descargada (tamaño: 2MB)"
    }
}
```
</details>

## Ejercicio 4: Cancelación Segura (Job & Finally)

Las corrutinas pueden cancelarse si el usuario sale de una pantalla o cancela una acción. Es vital limpiar recursos.

**Tarea:**

1.  Crea un `Job` mediante `launch` que ejecute un bucle infinito (o un `repeat(100)`) que imprima "Procesando archivo...".
2.  Añade un bloque `try-finally` dentro de la corrutina.
3.  En el `finally`, imprime "Limpiando archivos temporales y cerrando conexiones...".
4.  En el `main`, deja que la corrutina trabaje 500ms y luego cancéscala.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 4: Cancelación y Limpieza ---")

    val miJob = launch(Dispatchers.Default) {
        // Tarea: Implementar bucle con try-finally
    }

    delay(500)
    println("El usuario ha pulsado el botón 'Cancelar'.")
    // Tarea: Cancelar el job y esperar a que termine la limpieza
    
    println("-------------------------------------------\n")
}
```

**Pistas:**

- `cancelAndJoin()` cancela la corrutina y suspende el `main` hasta que los bloques `finally` de la corrutina terminen.
- Si no usas `yield()` o funciones de suspensión como `delay()` dentro del bucle, la corrutina podría ser "no cancelable" si no compruebas `isActive`.

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 4: Cancelación y Limpieza ---")

    val miJob = launch(Dispatchers.Default) {
        try {
            repeat(100) { i ->
                println("  [Corrutina] Procesando parte $i...")
                delay(100)
            }
        } finally {
            // Este bloque se ejecuta SIEMPRE, incluso tras la cancelación
            println("  [Corrutina] Limpiando archivos temporales y cerrando conexiones...")
        }
    }

    delay(350) // Dejamos que haga unas cuantas iteraciones
    println("  [Main] El usuario ha pulsado el botón 'Cancelar'.")
    
    miJob.cancelAndJoin()
    
    println("  [Main] Proceso de cancelación verificado.")
    println("-------------------------------------------\n")
}
```
</details>

## Ejercicio 5: Simulador de Carga de Datos (Combinado)

Vamos a simular un escenario real: cargar un perfil de usuario, sus publicaciones y sus amigos, todo de forma eficiente.

**Tarea:**

1.  Crea tres funciones: `fetchUser()`, `fetchPosts()` y `fetchFriends()`. Cada una debe tardar entre 500ms y 1000ms y devolver un String simple.
2.  En el `main`, queremos obtener los datos. Pero hay una condición:
    - Primero DEBES obtener el usuario.
    - UNA VEZ tengas el usuario, puedes descargar sus publicaciones y sus amigos **en paralelo** (ya que no dependen entre sí).
3.  Imprime el resultado final combinado y el tiempo total.

Función main

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 5: Carga de Datos Combinada ---")
    val inicio = System.currentTimeMillis()

    // 1. Obtener usuario (Secuencial)
    
    // 2. Obtener Posts y Amigos (En paralelo)
    
    // 3. Mostrar resultado final

    val fin = System.currentTimeMillis()
    println("Tiempo total de carga: ${fin - inicio} ms")
    println("---------------------------------------------\n")
}
```

**Pistas:**

- Usa `async` solo para la parte que puede ser paralela.
- El usuario debe obtenerse antes porque (teóricamente) necesitarías su ID para las otras dos llamadas.

<details>
<summary><b>Solución Ejercicio 5</b></summary>

```kotlin
fun main() = runBlocking {
    println("--- EJERCICIO 5: Carga de Datos Combinada ---")
    val inicio = System.currentTimeMillis()

    println("Iniciando carga de perfil...")

    // 1. Obtener usuario de forma secuencial
    val usuario = fetchUser()
    println("  Paso 1 listo: $usuario")

    // 2. Obtener Posts y Amigos en paralelo
    val deferredPosts = async { fetchPosts(usuario) }
    val deferredFriends = async { fetchFriends(usuario) }

    // Esperamos a ambos
    val posts = deferredPosts.await()
    val amigos = deferredFriends.await()

    // 3. Mostrar resultado
    println("  Paso 2 listo: Datos de posts y amigos recibidos.")
    println("\nRESUMEN DEL PERFIL:")
    println("Usuario: $usuario")
    println("Publicaciones: $posts")
    println("Amigos: $amigos")

    val fin = System.currentTimeMillis()
    println("\nTiempo total de carga: ${fin - inicio} ms")
    println("---------------------------------------------\n")
}

suspend fun fetchUser(): String {
    delay(800)
    return "Usuario_Kotlin_99"
}

suspend fun fetchPosts(user: String): String {
    delay(1000)
    return "Lista de 15 posts de $user"
}

suspend fun fetchFriends(user: String): String {
    delay(1000)
    return "Lista de 250 amigos de $user"
}
```
</details>

---

**¡Enhorabuena!** Has completado los ejercicios de corrutinas. Dominar estos conceptos te permitirá crear aplicaciones Android mucho más fluidas y reactivas.
