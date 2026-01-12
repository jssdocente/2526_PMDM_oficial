# Ejercicios: Clases Selladas e Interfaces Selladas

En esta serie de ejercicios profundizaremos en el uso de `sealed class` y `sealed interface` para modelar estados y eventos de forma segura y exhaustiva, algo fundamental en el desarrollo con Kotlin y Jetpack Compose.

## Ejercicio 1: Estado de una Conexión de Red (Sealed Class Básica)

Es muy común tener que representar si una aplicación está conectada, desconectada o intentando conectar.

**Tarea:**

1.  Crea una `sealed class` llamada `EstadoRed`.
2.  Define tres estados:
    - `Conectado`: un `object` que represente que hay conexión.
    - `Desconectado`: una `data class` que contenga un `motivo: String`.
    - `Reconectando`: un `object` que indique que se está intentando recuperar la señal.
3.  Crea una función `mostrarMensajeRed(estado: EstadoRed)` que use un `when` para imprimir un mensaje apropiado según el estado.
4.  En el `main`, simula el paso por los tres estados.
    

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 1: Estado de Red ---")
    val estados = listOf(
        EstadoRed.Reconectando,
        EstadoRed.Conectado,
        EstadoRed.Desconectado("Sin cobertura en el túnel")
    )

    estados.forEach { mostrarMensajeRed(it) }
}
```

**Pistas:**

- Usa `object` para estados que no necesitan datos adicionales y `data class` para los que sí.
- Recuerda que el `when` es exhaustivo con clases selladas.

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
fun main() {
    println("--- EJERCICIO 1: Estado de Red ---")
    val estados = listOf(
        EstadoRed.Reconectando,
        EstadoRed.Conectado,
        EstadoRed.Desconectado("Sin cobertura en el túnel")
    )

    estados.forEach { mostrarMensajeRed(it) }
}

sealed class EstadoRed {
    object Conectado : EstadoRed()
    data class Desconectado(val motivo: String) : EstadoRed()
    object Reconectando : EstadoRed()
}

fun mostrarMensajeRed(estado: EstadoRed) {
    when (estado) {
        EstadoRed.Conectado -> println("Conexión establecida con éxito.")
        is EstadoRed.Desconectado -> println("Desconectado: ${estado.motivo}")
        EstadoRed.Reconectando -> println("Buscando señal...")
    }
}
```
</details>

## Ejercicio 2: Acciones de un Reproductor de Música (Sealed Class y Data Class)

Imagina que estás diseñando los eventos que un usuario puede enviar a un reproductor de música.

**Tarea:**

1.  Crea una `sealed class` llamada `PlayerEvent`.
2.  Define las siguientes acciones:
    - `Play`: `object`.
    - `Pause`: `object`.
    - `SeekTo`: `data class` con una propiedad `posicion: Long`.
    - `ChangeTrack`: `data class` con una propiedad `trackName: String`.
3.  Implementa una función `handleEvent(event: PlayerEvent)` que simule la acción imprimiendo un log por consola.
4.  Prueba el funcionamiento en la función `main`.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 2: Reproductor de Música ---")
    handleEvent(PlayerEvent.Play)
    handleEvent(PlayerEvent.ChangeTrack("Bohemian Rhapsody"))
    handleEvent(PlayerEvent.SeekTo(120000))
    handleEvent(PlayerEvent.Pause)
}
```

**Pistas:**

- Este patrón se usa mucho en arquitecturas MVI (Model-View-Intent) para enviar acciones desde la UI al ViewModel.

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
fun main() {
    println("--- EJERCICIO 2: Reproductor de Música ---")
    handleEvent(PlayerEvent.Play)
    handleEvent(PlayerEvent.ChangeTrack("Bohemian Rhapsody"))
    handleEvent(PlayerEvent.SeekTo(120000))
    handleEvent(PlayerEvent.Pause)
}

sealed class PlayerEvent {
    object Play : PlayerEvent()
    object Pause : PlayerEvent()
    data class SeekTo(val posicion: Long) : PlayerEvent()
    data class ChangeTrack(val trackName: String) : PlayerEvent()
}

fun handleEvent(event: PlayerEvent) {
    when (event) {
        PlayerEvent.Play -> println("Reproduciendo música...")
        PlayerEvent.Pause -> println("Música pausada.")
        is PlayerEvent.SeekTo -> println("Saltando al milisegundo ${event.posicion}.")
        is PlayerEvent.ChangeTrack -> println("Cambiando a la pista: ${event.trackName}.")
    }
}
```
</details>

## Ejercicio 3: Respuesta Genérica de API (Sealed Class & Generics)

A menudo las peticiones a un servidor pueden devolver distintos tipos de datos, pero la estructura del resultado (Cargando, Éxito, Error) suele ser la misma.

**Tarea:**

1.  Crea una `sealed class` genérica `ApiResponse<T>`.
2.  Define las subclases:
    - `Success<T>`: `data class` que contiene los datos de tipo `T`.
    - `Error`: `data class` que contiene un `mensaje: String` y un `codigo: Int`.
    - `Loading`: `object` (o clase simple si prefieres mantener el genérico).
3.  Simula una función que devuelva un `ApiResponse<String>` con un nombre de usuario y otra que devuelva un `ApiResponse<Int>` con una edad.
4.  En el `main`, procesa ambos resultados usando `when`.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 3: Respuesta API Genérica ---")
    
    val respNombre: ApiResponse<String> = ApiResponse.Success("Juan García")
    val respError: ApiResponse<Int> = ApiResponse.Error("Usuario no encontrado", 404)

    procesarRespuesta(respNombre)
    procesarRespuesta(respError)
}
```

**Pistas:**

- Para que `Loading` funcione como `object` dentro de una clase sellada genérica, a veces es necesario usar `ApiResponse<Nothing>` o declarar `Loading<T> : ApiResponse<T>()`.

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
fun main() {
    println("--- EJERCICIO 3: Respuesta API Genérica ---")
    
    val respNombre: ApiResponse<String> = ApiResponse.Success("Juan García")
    val respError: ApiResponse<Int> = ApiResponse.Error("Usuario no encontrado", 404)

    procesarRespuesta(respNombre)
    procesarRespuesta(respError)
}

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val mensaje: String, val codigo: Int) : ApiResponse<T>()
    class Loading<T> : ApiResponse<T>()
}

fun <T> procesarRespuesta(response: ApiResponse<T>) {
    when (response) {
        is ApiResponse.Success -> println("Éxito: Datos recibidos -> ${response.data}")
        is ApiResponse.Error -> println("Error ${response.codigo}: ${response.mensaje}")
        is ApiResponse.Loading -> println("Cargando datos...")
    }
}
```
</details>

## Ejercicio 4: Sistema de Notificaciones (Sealed Interface)

Las interfaces selladas permiten que clases que ya heredan de otras puedan pertenecer también a una jerarquía cerrada.

**Tarea:**

1.  Crea una `sealed interface` llamada `Notificable`.
2.  Crea dos clases:
    - `MensajeTexto`: que tenga `emisor: String` y `contenido: String`.
    - `AlertaSistema`: que tenga `nivel: Int` (1-5) y `descripcion: String`.
3.  Ambas deben implementar `Notificable`.
4.  Crea una función `enviarNotificacion(n: Notificable)` que muestre la notificación de forma distinta según el tipo.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 4: Sistema de Notificaciones ---")
    val n1 = MensajeTexto("Ana", "¿Vienes a cenar?")
    val n2 = AlertaSistema(5, "Memoria crítica en el servidor")

    enviarNotificacion(n1)
    enviarNotificacion(n2)
}
```

**Pistas:**

- La principal diferencia con `sealed class` es que las interfaces no pueden almacenar estado (propiedades con valor), aunque sí pueden definir propiedades que las subclases deben implementar.

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
fun main() {
    println("--- EJERCICIO 4: Sistema de Notificaciones ---")
    val n1 = MensajeTexto("Ana", "¿Vienes a cenar?")
    val n2 = AlertaSistema(5, "Memoria crítica en el servidor")

    enviarNotificacion(n1)
    enviarNotificacion(n2)
}

sealed interface Notificable

data class MensajeTexto(val emisor: String, val contenido: String) : Notificable
data class AlertaSistema(val nivel: Int, val descripcion: String) : Notificable

fun enviarNotificacion(n: Notificable) {
    when (n) {
        is MensajeTexto -> println("Nuevo mensaje de ${n.emisor}: ${n.contenido}")
        is AlertaSistema -> println("ALERTA (Nivel ${n.nivel}): ${n.descripcion}")
    }
}
```
</details>

## Ejercicio 5: Flujo de Pedidos (Combinado)

Vamos a modelar el proceso de un pedido en una tienda online.

**Tarea:**

1.  Crea una `sealed class` llamada `EstadoPedido`.
2.  Define los estados:
    - `Procesando`: `object`.
    - `Enviado`: `data class` con `codigoSeguimiento: String`.
    - `Entregado`: `object`.
    - `Cancelado`: `data class` con `razon: String`.
3.  Crea una clase `Pedido` que tenga un `id: Int` y un `estado: EstadoPedido`.
4.  Añade una función a `Pedido` llamada `actualizarEstado(nuevoEstado: EstadoPedido)` y otra `imprimirTicket()` que use el `when` para dar detalles del estado actual.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 5: Flujo de Pedido ---")
    val miPedido = Pedido(1001)
    
    miPedido.imprimirTicket()
    
    miPedido.actualizarEstado(EstadoPedido.Enviado("TRACK-99-XYZ"))
    miPedido.imprimirTicket()
    
    miPedido.actualizarEstado(EstadoPedido.Entregado)
    miPedido.imprimirTicket()
}
```

**Pistas:**

- Puedes incluir lógica dentro de la clase `Pedido` para gestionar cómo cambia el estado.

<details>
<summary><b>Solución Ejercicio 5</b></summary>

```kotlin
fun main() {
    println("--- EJERCICIO 5: Flujo de Pedido ---")
    val miPedido = Pedido(1001)
    
    miPedido.imprimirTicket()
    
    miPedido.actualizarEstado(EstadoPedido.Enviado("TRACK-99-XYZ"))
    miPedido.imprimirTicket()
    
    miPedido.actualizarEstado(EstadoPedido.Entregado)
    miPedido.imprimirTicket()
}

sealed class EstadoPedido {
    object Procesando : EstadoPedido()
    data class Enviado(val codigoSeguimiento: String) : EstadoPedido()
    object Entregado : EstadoPedido()
    data class Cancelado(val razon: String) : EstadoPedido()
}

class Pedido(val id: Int) {
    var estado: EstadoPedido = EstadoPedido.Procesando

    fun actualizarEstado(nuevoEstado: EstadoPedido) {
        estado = nuevoEstado
        println("  [Sistema] El pedido $id ha pasado a estado: ${estado.javaClass.simpleName}")
    }

    fun imprimirTicket() {
        print("Ticket Pedido #$id: ")
        when (val e = estado) {
            EstadoPedido.Procesando -> println("Estamos preparando tu paquete.")
            is EstadoPedido.Enviado -> println("En camino. Sigue tu envío con el código: ${e.codigoSeguimiento}")
            EstadoPedido.Entregado -> println("El paquete ha sido entregado. ¡Gracias!")
            is EstadoPedido.Cancelado -> println("Pedido cancelado por: ${e.razon}")
        }
    }
}
```
</details>

---

**¡Buen trabajo!** Con estos ejercicios habrás dominado el uso de las jerarquías selladas en Kotlin, una herramienta indispensable para escribir código robusto y libre de errores inesperados en los flujos de control.
