# Ejercicios: Objects, Companion Objects y Sealed Classes

Aquí tenéis una serie de ejercicios que deberéis resolver. Intentad comprender el "por qué" detrás de cada solución, no solo el "cómo".

## Ejercicio 1: Gestor de Temas (Object & Sealed Class)

En vuestra aplicación Compose, querréis manejar diferentes temas (claro, oscuro, sistema). Utilizaremos un `object` como singleton para gestionar el tema actual y una `sealed class` para representar los posibles temas.

**Tarea:**

1.  Crea una `sealed class` llamada `TemaApp` con los siguientes objetos: `Claro`, `Oscuro`, `Sistema`.
2.  Crea un `object` llamado `TemaManager` que:
    - Tenga una propiedad `temaActual` de tipo `TemaApp` inicializada a `TemaApp.Sistema`.
    - Tenga una función `cambiarTema(nuevoTema: TemaApp)` que actualice `temaActual` e imprima por consola el tema seleccionado.
    - Tenga una función `mostrarTemaActual()` que imprima el tema que está actualmente activo.
3.  En la función `main`, simula el uso del `TemaManager`:
    - Muestra el tema actual inicial.
    - Cambia el tema a `Oscuro` y luego lo muestra.
    - Cambia el tema a `Claro` y luego lo muestra.

**Pistas:**

- Las `object` dentro de una `sealed class` son perfectas para representar estados fijos.
- Un `object` singleton es ideal para gestionar un estado global como la configuración del tema.

## Ejercicio 2: Validador de Entradas (Companion Object)

Cuando un usuario introduce datos en un formulario, a menudo necesitamos validarlos (por ejemplo, que no esté vacío, que sea un número, etc.). Vamos a crear una clase `FormularioInput` y usaremos un `companion object` para proporcionar funciones de validación.

**Tarea:**

1.  Crea una clase `FormularioInput` con una propiedad `valor: String`.
2.  Dentro de `FormularioInput`, crea un `companion object` que contenga las siguientes funciones:
    - `validarNoVacio(texto: String): Boolean` que devuelva `true` si el texto no está vacío ni solo contiene espacios en blanco.
    - `validarEsNumero(texto: String): Boolean` que devuelva `true` si el texto se puede convertir a un número entero.
    - `validarLongitudMinima(texto: String, longitud: Int): Boolean` que devuelva `true` si la longitud del texto es mayor o igual a `longitud`.
3.  En la función `main`, crea algunas cadenas de texto de ejemplo y utiliza las funciones del `companion object` para validarlas, imprimiendo los resultados.

**Pistas:**

- El `companion object` es perfecto para funciones de utilidad que no requieren una instancia de la clase, como las validaciones.
- Puedes usar `toIntOrNull()` para intentar convertir una cadena a número.

## Ejercicio 3: Registrador de Eventos (Object & Companion Object)

En una aplicación, es útil tener un sistema de registro (logging) para ver lo que está sucediendo. Crearemos una clase para eventos y un `object` singleton para el registrador.

**Tarea:**

1.  Crea una `sealed class` llamada `EventoApp` con las siguientes subclases de datos:
    - `ClickBoton(idBoton: String)`
    - `PantallaMostrada(nombrePantalla: String)`
    - `Error(mensajeError: String, codigo: Int)`
2.  Crea un `object` llamado `Logger` que:
    - Tenga una lista mutable (`MutableList`) para almacenar los `EventoApp` registrados.
    - Tenga una función `registrarEvento(evento: EventoApp)` que añada el evento a la lista e imprima el evento por consola.
    - Tenga una función `mostrarTodosLosEventos()` que itere sobre la lista e imprima cada evento.
    - Tenga un `companion object` con una función de extensión `EventoApp.Companion.crearEventoGenerico(tipo: String, detalles: String)` que devuelva un `Error` si el `tipo` es "error", o un `ClickBoton` o `PantallaMostrada` basados en el `tipo` y `detalles` (puedes simplificar la lógica de creación para este ejercicio). Si el tipo no coincide, que devuelva un `Error` genérico.

**Pistas:**

- `sealed class` es ideal para modelar un conjunto finito de eventos distintos.
- El `object` `Logger` actúa como un singleton para centralizar el registro.
- Las funciones de extensión en `companion object` te permiten añadir métodos de "fábrica" o utilidades a la propia `sealed class`.

## Ejercicio 4: Generador de IDs Únicos (Object)

En muchas situaciones, necesitamos generar IDs únicos para diferentes elementos de nuestra aplicación.

**Tarea:**

1.  Crea un `object` llamado `IdGenerator` que:
    - Tenga una propiedad privada `_currentId: Int` inicializada a `0`.
    - Tenga una función `generarNuevoId(): Int` que incremente `_currentId` y devuelva el nuevo valor.
    - Tenga una función `resetearGenerador()` que reinicie `_currentId` a `0`.
2.  En la función `main`, realiza lo siguiente:
    - Genera e imprime 3 IDs.
    - Resetea el generador.
    - Genera e imprime otros 2 IDs para verificar que se han reseteado.

**Pistas:**

- Un `object` es perfecto para esta tarea porque solo necesitas una única instancia del generador de IDs en toda tu aplicación.

---

**¡Mucho éxito con los ejercicios!** Una vez que los hayáis completado, compara vuestras soluciones con las de vuestros compañeros. ¡Es una excelente forma de aprender diferentes enfoques!
