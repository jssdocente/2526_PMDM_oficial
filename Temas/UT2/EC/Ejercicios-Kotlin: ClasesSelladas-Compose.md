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

**Pista Compose:** En Compose, usaríais `remember { mutableStateOf(DownloadButtonState.Initial) }` para mantener el estado y un `when` dentro de vuestro `@Composable` para cambiar la apariencia del botón.

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

**Pista Compose:** En un `Composable` de ítem de lista (`TaskItem`), podríais tener un `onClick` en un Checkbox que envía `TaskInteraction.ToggleCompleted(task.id)` a un `ViewModel` o a una función de manejo de eventos.

## Ejercicio 3: Diálogo de Alerta Genérico

En Compose, a menudo necesitaréis mostrar diálogos de alerta con diferentes mensajes y acciones. Podemos usar una `sealed class` genérica para definir estos diálogos.

**Tarea:**

1.  Define una `sealed class` genérica `AlertDialogState<T>` (donde `T` podría ser el tipo de dato relacionado con la acción del diálogo) con las siguientes subclases:
    - `object Dismissed : AlertDialogState<Nothing>()`: El diálogo no se muestra.
    - `data class ShowConfirm(val title: String, val message: String, val onConfirm: () -> Unit, val onDismiss: () -> Unit) : AlertDialogState<Nothing>()`: Muestra un diálogo de confirmación con título, mensaje y acciones para confirmar/descartar.
    - `data class ShowInfo(val title: String, val message: String, val onDismiss: () -> Unit) : AlertDialogState<Nothing>()`: Muestra un diálogo informativo.
2.  Crea una función `showDialogInConsole(state: AlertDialogState<*>)` que, basándose en el estado, imprima por consola el diálogo que se "mostraría". Si el estado es `Dismissed`, no debe imprimir nada. Para `ShowConfirm` y `ShowInfo`, imprime el título y el mensaje.
3.  En la función `main`, simula la muestra y ocultación de diferentes tipos de diálogos, llamando a `showDialogInConsole()` en cada cambio de estado. Incluye las acciones `onConfirm` y `onDismiss` como lambdas simples que impriman un mensaje.

**Pista Compose:** En un `Composable` raíz, tendríais un `mutableStateOf` que contiene vuestro `AlertDialogState`. Luego, usaríais un `when` y las funciones `AlertDialog` o `BasicAlertDialog` de Compose para mostrar el diálogo correspondiente.

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

**Pista Compose:** Esta es una forma estándar de manejar los resultados de `Flows` o `LiveData` de vuestros `ViewModels`. En vuestro `Composable`, un `when` sobre `ApiResult` dictará qué UI mostrar (un `CircularProgressIndicator`, los datos o un mensaje de error).

---

¡Recordad que la clave está en entender cómo las `sealed classes` os dan una forma controlada y segura de representar estados y eventos en vuestras aplicaciones Compose! ¡A programar!
