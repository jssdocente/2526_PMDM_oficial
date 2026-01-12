# Ejercicios: Sealed Classes para Compose

## Ejercicio 1: Estado de un Botón de Descarga

Imaginad que tenéis un botón en vuestra app que inicia una descarga. Este botón necesita mostrar diferentes estados (Inicial, Descargando, Descarga Completa, Error).

**Tarea:**

1.  Define una `sealed class` llamada `DownloadButtonState` que represente los posibles estados del botón:
    - `object Initial`: El botón está listo para iniciar la descarga.
    - `object Downloading`: La descarga está en progreso.
    - `data class Downloaded(val filePath: String)`: La descarga ha finalizado con éxito, y se conoce la ruta del archivo.
    - `data class Error(val errorMessage: String)`: Ha ocurrido un error durante la descarga.
2.  Crea una función llamada `simulateDownload()` que acepte un `DownloadButtonState` como parámetro y use una expresión `when` para "dibujar" el botón en la consola según su estado. Por ejemplo:
    - `Initial`: "Botón: Descargar"
    - `Downloading`: "Botón: Descargando..."
    - `Downloaded`: "Botón: Abrir archivo (${filePath})"
    - `Error`: "Botón: Reintentar (Error: ${errorMessage})"
3.  En la función `main`, simula el flujo de descarga:
    - Empieza en `Initial`.
    - Pasa a `Downloading`.
    - Simula un `Downloaded` con una ruta de archivo.
    - Finalmente, simula un `Error` con un mensaje.
    - Llama a `simulateDownload()` en cada cambio de estado.

- **Pista Compose:** En Compose, usaríais `remember { mutableStateOf(DownloadButtonState.Initial) }` para mantener el estado y un `when` dentro de vuestro `@Composable` para cambiar la apariencia del botón.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 1: Botón de Descarga ---")
    
    // Simular el flujo
    var estadoBoton: DownloadButtonState = DownloadButtonState.Initial
    simulateDownload(estadoBoton)

    estadoBoton = DownloadButtonState.Downloading
    simulateDownload(estadoBoton)

    estadoBoton = DownloadButtonState.Downloaded("/sdcard/mis_fotos/gato.jpg")
    simulateDownload(estadoBoton)

    estadoBoton = DownloadButtonState.Error("No hay conexión a internet")
    simulateDownload(estadoBoton)

    println("--------------------------------------\n")
}
```

<details>
<summary><b>Solución Ejercicio 1</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 1: Botón de Descarga ---")
    
    // Simular el flujo
    var estadoBoton: DownloadButtonState = DownloadButtonState.Initial
    simulateDownload(estadoBoton)

    estadoBoton = DownloadButtonState.Downloading
    simulateDownload(estadoBoton)

    estadoBoton = DownloadButtonState.Downloaded("/sdcard/mis_fotos/gato.jpg")
    simulateDownload(estadoBoton)

    estadoBoton = DownloadButtonState.Error("No hay conexión a internet")
    simulateDownload(estadoBoton)

    println("--------------------------------------\n")
}

// 1. Sealed Class
sealed class DownloadButtonState {
    object Initial : DownloadButtonState()
    object Downloading : DownloadButtonState()
    data class Downloaded(val filePath: String) : DownloadButtonState()
    data class Error(val errorMessage: String) : DownloadButtonState()
}

// 2. Función simulateDownload
fun simulateDownload(state: DownloadButtonState) {
    val textoBoton = when (state) {
        is DownloadButtonState.Initial -> "Botón: Descargar"
        is DownloadButtonState.Downloading -> "Botón: Descargando..."
        is DownloadButtonState.Downloaded -> "Botón: Abrir archivo (${state.filePath})"
        is DownloadButtonState.Error -> "Botón: Reintentar (Error: ${state.errorMessage})"
    }
    println(textoBoton)
}
```
</details>

## Ejercicio 2: Interacciones de Usuario en una Lista de Tareas

Estáis creando una lista de tareas (To-Do List). Vuestros ítems de tarea pueden tener varias interacciones: marcarlos como completados, eliminarlos o editarlos.

**Tarea:**

1.  Crea una `data class` llamada `Task` con propiedades `id: Int`, `description: String`, y `isCompleted: Boolean`.
2.  Define una `sealed class` llamada `TaskInteraction` para representar las acciones que el usuario puede realizar sobre una tarea:
    - `data class ToggleCompleted(val taskId: Int)`: El usuario ha marcado/desmarcado una tarea.
    - `data class DeleteTask(val taskId: Int)`: El usuario ha eliminado una tarea.
    - `data class EditTask(val taskId: Int, val newDescription: String)`: El usuario ha editado la descripción de una tarea.
3.  Crea una función `handleTaskInteraction(interaction: TaskInteraction)` que simule el procesamiento de estas interacciones:
    - Imprime un mensaje específico para cada tipo de interacción (ej. "Marcando tarea ID X como completada/incompleta").
4.  En la función `main`, simula algunas interacciones de usuario y llama a `handleTaskInteraction()` para cada una.

- **Pista Compose:** En un `Composable` de ítem de lista (`TaskItem`), podríais tener un `onClick` en un Checkbox que envía `TaskInteraction.ToggleCompleted(task.id)` a un `ViewModel` o a una función de manejo de eventos.

```kotlin
fun main() {
    println("--- EJERCICIO 2: Interacciones To-Do List ---")
    
    handleTaskInteraction(TaskInteraction.ToggleCompleted(101))
    handleTaskInteraction(TaskInteraction.EditTask(101, "Comprar leche y pan"))
    handleTaskInteraction(TaskInteraction.DeleteTask(101))

    println("-------------------------------------------\n")
}
```

<details>
<summary><b>Solución Ejercicio 2</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 2: Interacciones To-Do List ---")
    
    handleTaskInteraction(TaskInteraction.ToggleCompleted(101))
    handleTaskInteraction(TaskInteraction.EditTask(101, "Comprar leche y pan"))
    handleTaskInteraction(TaskInteraction.DeleteTask(101))

    println("-------------------------------------------\n")
}

// 1. Data Class Task
data class Task(val id: Int, val description: String, val isCompleted: Boolean)

// 2. Sealed Class TaskInteraction
sealed class TaskInteraction {
    data class ToggleCompleted(val taskId: Int) : TaskInteraction()
    data class DeleteTask(val taskId: Int) : TaskInteraction()
    data class EditTask(val taskId: Int, val newDescription: String) : TaskInteraction()
}

// 3. Función handleTaskInteraction
fun handleTaskInteraction(interaction: TaskInteraction) {
    when (interaction) {
        is TaskInteraction.ToggleCompleted -> {
            println("  -> Marcando tarea ${interaction.taskId} como completada/incompleta.")
        }
        is TaskInteraction.DeleteTask -> {
            println("  -> Eliminando tarea ${interaction.taskId} permanentemente.")
        }
        is TaskInteraction.EditTask -> {
            println("  -> Actualizando descripción de tarea ${interaction.taskId} a: '${interaction.newDescription}'")
        }
    }
}
```
</details>

## Ejercicio 3: Diálogo de Alerta Genérico

En Compose, a menudo necesitaréis mostrar diálogos de alerta con diferentes mensajes y acciones. Podemos usar una `sealed class` genérica para definir estos diálogos.

**Tarea:**

1.  Define una `sealed class` genérica `AlertDialogState<T>` (donde `T` podría ser el tipo de dato relacionado con la acción del diálogo) con las siguientes subclases:
    - `object Dismissed : AlertDialogState<Nothing>()`: El diálogo no se muestra.
    - `data class ShowConfirm(val title: String, val message: String, val onConfirm: () -> Unit, val onDismiss: () -> Unit) : AlertDialogState<Nothing>()`: Muestra un diálogo de confirmación con título, mensaje y acciones para confirmar/descartar.
    - `data class ShowInfo(val title: String, val message: String, val onDismiss: () -> Unit) : AlertDialogState<Nothing>()`: Muestra un diálogo informativo.
2.  Crea una función `showDialogInConsole(state: AlertDialogState<*>)` que, basándose en el estado, imprima por consola el diálogo que se "mostraría". Si el estado es `Dismissed`, no debe imprimir nada. Para `ShowConfirm` y `ShowInfo`, imprime el título y el mensaje.
3.  En la función `main`, simula la muestra y ocultación de diferentes tipos de diálogos, llamando a `showDialogInConsole()` en cada cambio de estado. Incluye las acciones `onConfirm` y `onDismiss` como lambdas simples que impriman un mensaje.

- **Pista Compose:** En un `Composable` raíz, tendríais un `mutableStateOf` que contiene vuestro `AlertDialogState`. Luego, usaríais un `when` y las funciones `AlertDialog` o `BasicAlertDialog` de Compose para mostrar el diálogo correspondiente.

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 3: Diálogos de Alerta ---")
    
    // Simular mostrar confirmación
    var dialogState: AlertDialogState<*> = AlertDialogState.ShowConfirm(
        title = "¿Eliminar?",
        message = "Esta acción no se puede deshacer.",
        onConfirm = { println("    [Acción] Elemento eliminado") },
        onDismiss = { println("    [Acción] Cancelado") }
    )
    showDialogInConsole(dialogState)

    // Simular mostrar información
    dialogState = AlertDialogState.ShowInfo(
        title = "Información",
        message = "Operación completada con éxito.",
        onDismiss = { println("    [Acción] Cerrado") }
    )
    showDialogInConsole(dialogState)

    // Simular ocultar el diálogo
    dialogState = AlertDialogState.Dismissed
    showDialogInConsole(dialogState)

    println("-----------------------------------------\n")
}
```

<details>
<summary><b>Solución Ejercicio 3</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 3: Diálogos de Alerta ---")
    
    // Simular mostrar confirmación
    var dialogState: AlertDialogState<*> = AlertDialogState.ShowConfirm(
        title = "¿Eliminar?",
        message = "Esta acción no se puede deshacer.",
        onConfirm = { println("    [Acción] Elemento eliminado") },
        onDismiss = { println("    [Acción] Cancelado") }
    )
    showDialogInConsole(dialogState)

    // Simular que el usuario descarta
    dialogState = AlertDialogState.Dismissed
    showDialogInConsole(dialogState) // No imprime nada

    // Simular información
    dialogState = AlertDialogState.ShowInfo(
        title = "Bienvenido",
        message = "Gracias por registrarte en la app.",
        onDismiss = { println("    [Acción] Info cerrada") }
    )
    showDialogInConsole(dialogState)

    println("--------------------------------------\n")
}

// 1. Sealed Class Genérica
sealed class AlertDialogState<out T> {
    object Dismissed : AlertDialogState<Nothing>()
    data class ShowConfirm(
        val title: String, 
        val message: String, 
        val onConfirm: () -> Unit, 
        val onDismiss: () -> Unit
    ) : AlertDialogState<Nothing>()
    
    data class ShowInfo(
        val title: String, 
        val message: String, 
        val onDismiss: () -> Unit
    ) : AlertDialogState<Nothing>()
}

// 2. Función showDialogInConsole
fun showDialogInConsole(state: AlertDialogState<*>) {
    when (state) {
        is AlertDialogState.Dismissed -> {
            // No hacer nada
        }
        is AlertDialogState.ShowConfirm -> {
            println("[DIALOG] Título: ${state.title}")
            println("         Mensaje: ${state.message}")
            println("         [Confirmar] [Cancelar]")
            // Simulamos click en confirmar para probar
            state.onConfirm() 
        }
        is AlertDialogState.ShowInfo -> {
            println("[DIALOG] Título: ${state.title}")
            println("         Mensaje: ${state.message}")
            println("         [OK]")
        }
    }
}
```
</details>

## Ejercicio 4: Resultado de una Operación de Red con Datos Opcionales

Cuando hacéis una llamada a una API, a veces recibís datos y a veces no (por ejemplo, al eliminar un recurso, la respuesta de éxito puede no contener datos útiles, solo un código 200). Queremos modelar esto con una `sealed class`.

**Tarea:**

1.  Crea una `data class` sencilla `Product(val id: String, val name: String, val price: Double)`.
2.  Define una `sealed class` genérica `ApiResult<out T>` para representar el resultado de una llamada a la API:
    - `object Loading : ApiResult<Nothing>()`
    - `data class Success<out T>(val data: T?) : ApiResult<T>()` (Nota el `data: T?` para permitir datos nulos)
    - `data class Error(val code: Int, val message: String) : ApiResult<Nothing>()`
3.  Crea una función `processApiResult(result: ApiResult<Product>)` que imprima por consola lo que ocurriría en la UI según el resultado:
    - `Loading`: "Cargando datos del producto..."
    - `Success`: Si `data` no es nulo, imprime los detalles del producto. Si es nulo, imprime "Operación exitosa sin datos específicos."
    - `Error`: Imprime el código de error y el mensaje.
4.  En la función `main`, simula diferentes resultados de API:
    - Un `Loading`.
    - Un `Success` con un objeto `Product`.
    - Un `Success` con `null` (simulando una operación exitosa sin retorno de datos, como un borrado).
    - Un `Error`.

- **Pista Compose:** Esta es una forma estándar de manejar los resultados de `Flows` o `LiveData` de vuestros `ViewModels`. En vuestro `Composable`, un `when` sobre `ApiResult` dictará qué UI mostrar (un `CircularProgressIndicator`, los datos o un mensaje de error).

Función main

```kotlin
fun main() {
    println("--- EJERCICIO 4: Resultados de API ---")
    
    processApiResult(ApiResult.Loading)
    
    val producto = Product("p1", "Laptop Gamer", 1500.0)
    processApiResult(ApiResult.Success(producto))
    
    // Éxito pero sin datos (ej. borrado exitoso)
    processApiResult(ApiResult.Success(null))

    processApiResult(ApiResult.Error(404, "Producto no encontrado"))

    println("-------------------------------------\n")
}
```

<details>
<summary><b>Solución Ejercicio 4</b></summary>

```kotlin
// main.kt
fun main() {
    println("--- EJERCICIO 4: Resultados de API ---")
    
    processApiResult(ApiResult.Loading)
    
    val producto = Product("p1", "Laptop Gamer", 1500.0)
    processApiResult(ApiResult.Success(producto))
    
    // Éxito pero sin datos (ej. borrado exitoso)
    processApiResult(ApiResult.Success(null))

    processApiResult(ApiResult.Error(404, "Producto no encontrado"))

    println("-------------------------------------\n")
}

// 1. Data Class Producto
data class Product(val id: String, val name: String, val price: Double)

// 2. Sealed Class ApiResult
sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<out T>(val data: T?) : ApiResult<T>()
    data class Error(val code: Int, val message: String) : ApiResult<Nothing>()
}

// 3. Función processApiResult
fun processApiResult(result: ApiResult<Product>) {
    when (result) {
        is ApiResult.Loading -> println("UI: Mostrando Spinner (Cargando datos del producto...)")
        is ApiResult.Success -> {
            if (result.data != null) {
                println("UI: Mostrando producto -> ${result.data.name} ($${result.data.price})")
            } else {
                println("UI: Mostrando mensaje -> Operación exitosa sin datos específicos.")
            }
        }
        is ApiResult.Error -> println("UI: Mostrando Error -> ${result.message} (Código ${result.code})")
    }
}
```
</details>

---

¡Recordad que la clave está en entender cómo las `sealed classes` os dan una forma controlada y segura de representar estados y eventos en vuestras aplicaciones Compose! ¡A programar!
