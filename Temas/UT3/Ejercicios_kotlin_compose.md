# UT3. Ejercicios Kotlin: De Funcional a Compose

Este documento contiene una serie de ejercicios diseñados para cerrar la brecha entre el Kotlin estándar y el desarrollo con **Jetpack Compose**.

Aunque Compose es un framework de UI para Android, se basa fuertemente en características del lenguaje Kotlin (Lambdas, Sealed Classes, Scope Functions). **Estos ejercicios pueden (y deben) realizarse en un proyecto estándar de Kotlin en IntelliJ IDEA**, usando la consola para simular la salida visual.

---

## 🏗️ Bloque 1: Funciones Lambda y Entendiendo la "UI como Código"

En Compose, la UI se define mediante funciones. Las lambdas son esenciales para pasar "bloques de UI" o escuchar eventos.

### 1.1. Calentamiento: `filter` y `map`
Las listas en Kotlin se manipulan funcionalmente. Esto es la base para transformar datos antes de mostrarlos en una lista de Compose.

**Ejercicio:**
1.  Crea una lista de números del 1 al 20.
2.  Usa `filter` para quedarte solo con los números pares.
3.  Usa `map` para transformar esos números en cadenas con el formato "Número: X".
4.  Imprime la lista resultante.

### 1.2. Trailing Lambdas y DSLs
En Kotlin, si el último parámetro de una función es una función, la lambda puede ir fuera de los paréntesis. Esto es lo que hace que Compose parezca tener una sintaxis anidada.

**Ejercicio:**
Define una función llamada `simularBoton` que reciba un `texto: String` y una función `accion: () -> Unit`.
```kotlin
// Firma sugerida
fun simularBoton(texto: String, accion: () -> Unit) { ... }
```
Llama a la función usando la sintaxis de **Trailing Lambda**:
```kotlin
simularBoton("Guardar") {
    println("Botón pulsado: Guardando datos...")
}
```

### 1.3. Patrón: Event Hoisting (Elevación de Eventos)
En Compose, un componente de UI no debe modificar sus propios datos, sino avisar a quien lo llamó.

**Ejercicio:**
Crea una clase `EntradaTexto` (simulada) que no tenga estado interno modificable.
1.  Debe recibir el `valorActual: String` y una función `onValorCambio: (String) -> Unit`.
2.  Simula que el usuario escribe algo nuevo llamando a `onValorCambio("Nuevo Texto")`.
3.  Imprime por consola qué valor tenía antes y qué evento se ha disparado.

### 1.4. Patrón: Slots (Huecos para UI)
Muchos componentes de Compose (como `Scaffold` o `Surface`) aceptan "contenido" genérico. Esto se hace pasando una función composable como parámetro.

**Ejercicio:**
Crea una función `EnvoltorioPantalla` que reciba un título y una función `contenido: () -> Unit`.
1.  La función debe imprimir una línea de "Encabezado: [Titulo]".
2.  Luego debe ejecutar la función `contenido()`.
3.  Finalmente imprimir una línea de "Pie de página".

Úsalo así:
```kotlin
EnvoltorioPantalla("Mi Aplicación") {
    println("  -> Aquí va el formulario de login")
    println("  -> Aquí va el botón de aceptar")
}
```

---

## 🔍 Bloque 2: Scope Functions (`apply`, `let`, `run`, `with`, `also`)

Estas funciones son omnipresentes para configurar modificadores (`Modifier.apply...`) o manejar nulos en la UI.

### 2.1. `let` para Null Safety
En una UI reactiva, a veces los datos no han cargado aún (`null`). `let` nos permite ejecutar código solo si el valor existe.

**Ejercicio:**
Tienes una variable `mensaje: String? = null`.
1.  Simula que carga un dato: `mensaje = "Hola mundo"`.
2.  Usa `let` para imprimir el mensaje en mayúsculas SOLAMENTE si no es nulo.
3.  Si es nulo, usa el operador elvis `?:` o `run` para imprimir "Cargando...".

### 2.2. `apply` para Configuración de Objetos
Sirve para inicializar objetos sin repetir el nombre de la variable. Muy usado en bloques `init` o constructores secundarios.

**Ejercicio:**
Define una clase `ConfiguracionBoton` con propiedades `color`, `texto` y `habilitado`.
Instancia la clase y usa `apply` para configurar sus valores en un solo bloque, asignando el resultado a una variable `miBoton`.

### 2.3. Diferenciando `run`, `with` y `also`
*   **`also`**: "Haz esto además..." (ej. logs). Devuelve el objeto original.
*   **`run`**: "Corre este bloque de lógica..." (cálculos). Devuelve el resultado de la última línea.

**Ejercicio:**
Crea una lista mutable de tareas `val tareas = mutableListOf("Comprar pan")`.
Usa una cadena de scope functions para:
1.  Añadir una tarea nueva (`.apply` o directo).
2.  Usar `.also` para imprimir "Se ha añadido una nueva tarea, total: X".
3.  Usar `.run` para devolver solo el primer elemento de la lista y convertirlo a mayúsculas.

---

## 🛡️ Bloque 3: Sealed Classes y Modelado de Estado

El patrón más robusto para UI en Android moderno es MVI (Model-View-Intent), donde el estado de la UI es una jerarquía cerrada.

### 3.1. Definiendo los Estados de la UI (`UiState`)
Las pantallas suelen tener 3 estados base: Cargando, Éxito (con datos) y Error.

**Ejercicio:**
Crea una `sealed class` llamada `PantallaHomeState`.
*   `object Cargando`: Sin datos.
*   `data class Exito(val noticias: List<String>)`: Contiene los datos a mostrar.
*   `data class Error(val mensaje: String)`: Contiene la razón del fallo.

### 3.2. Consumiendo el Estado con `when`
La magia de las sealed classes es el `when` exhaustivo (el compilador te obliga a cubrir todos los casos).

**Ejercicio:**
Crea una función estática (fuera de clase) llamada `renderizar(estado: PantallaHomeState)`.
Usa `when (estado)` para imprimir un "dibujo" simulado de la pantalla:
*   Si es `Cargando`: Imprime un spinner de texto `[ ... ]`.
*   Si es `Exito`: Imprime la lista de noticias formateada.
*   Si es `Error`: Imprime el mensaje de error en rojo (o con exclamaciones `!!! ERROR: ... !!!`).

---

## 🚀 Bloque 4: Ejercicio Integrador - "Simulador de Noticias"

Vamos a construir un **ciclo completo unidireccional** (Unidirectional Data Flow) en consola.

**Arquitectura:**
1.  **Estado (`Sealed Class`)**: `NoticiasState`.
2.  **Gestor de Lógica ("ViewModel")**: Una clase que tiene el estado actual y funciones para cambiarlo.
3.  **UI ("Composable")**: Una función que recibe el estado y callbacks.

### Implementación Paso a Paso:

**Paso 1: Define el Estado**
`NoticiasState`: `Cargando`, `Vacio`, `Datos(val lista: List<String>)`, `Error(val msg: String)`.

**Paso 2: Clase `GestorNoticias`**
Debe tener una propiedad `var estado: NoticiasState`.
Métodos para simular acciones:
*   `cargar()`: Pone estado en `Cargando`, y simula (puedes no usar delay real o sí) pasar a `Datos` o `Error`.
*   `borrarTodo()`: Pone el estado en `Vacio`.

**Paso 3: Función `PantallaNoticias` (Componente UI)**
Firmas sugerida:
```kotlin
fun PantallaNoticias(
    estado: NoticiasState,
    onCargarClick: () -> Unit,
    onBorrarClick: () -> Unit
)
```
Dentro, usa `when(estado)` para imprimir.
Al final, "imprime" un menú simulado para el usuario:
`[1] Cargar Datos  [2] Borrar Todo  [3] Salir`

**Paso 4: El `main` Loop**
En la función `main`:
1.  Instancia el `GestorNoticias`.
2.  Crea un bucle `while(true)`.
3.  Llama a `PantallaNoticias` pasando el estado del gestor.
    *   **Importante:** Para los callbacks (`onCargarClick`), pasa lambdas que llamen a los métodos del gestor (`gestor.cargar()`).
    *   *Nota: Como es consola, necesitarás un `readLine()` dentro del main para capturar qué opción elige el usuario y ejecutar la lambda correspondiente manualmente, o simplemente simúlalo llamando a las funciones secuencialmente.*

**Objetivo:** Debes ver cómo el estado fluye hacia abajo (a la función Pantalla) y los eventos fluyen hacia arriba (al Gestor), modificando el estado y volviendo a pintar. ¡Es la esencia de Compose!
