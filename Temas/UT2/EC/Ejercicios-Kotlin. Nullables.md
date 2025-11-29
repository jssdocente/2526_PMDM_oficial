# 🖥️ Ejercicios: Seguridad ante Nulos (Null Safety)

## 📝 Introducción Teórica (Breve Repaso)

En Java (y otros lenguajes), cualquier objeto podía ser `null`, provocando cierres inesperados de la App. Kotlin diferencia los tipos en dos grupos:

1.  **Tipos No-Nulables (Por defecto):** `String`, `Int`, `User`. **NO** aceptan nulos.
2.  **Tipos Nulables (`?`):** `String?`, `Int?`, `User?`. **SÍ** aceptan nulos.

Herramientas que usaremos:

- **`?`**: Para declarar una variable que puede estar vacía.
- **`?.` (Safe Call)**: "Si no es nulo, accede. Si es nulo, devuelve null (sin explotar)".
- **`?:` (Operador Elvis)**: "Si lo de la izquierda es nulo, usa el valor por defecto de la derecha".
- **`!!. `(Not-null assertion)**: "Te juro que no es nulo (y si miento, explota)". **¡Peligro!**

---

## Ejercicio 1: Declaración y Asignación

**Contexto:** Estás definiendo el modelo de datos de un alumno. Algunos datos son obligatorios y otros opcionales.

1. Crea una variable inmutable `nombre` de tipo `String` e intenta asignarle un `null`. Observa el error del compilador.
2. Crea una variable `apellido` que permita nulos e inicialízala a `null`.
3. Crea una variable `matricula` que permita nulos, dale un valor entero (ej. 2024) y luego cámbiala a `null`.

```kotlin
fun main() {
    // TODO: Tu código aquí

    println("Ejercicio 1 completado (si compila)")
}
```

---

## Ejercicio 2: El Operador Elvis (`?:`)

**Contexto:** En una pantalla de Compose, necesitamos mostrar el nombre de usuario. Si el usuario no ha configurado un nombre (es nulo), debemos mostrar el texto "Invitado".

1. Crea una variable `usuario: String?` inicializada a `null`.
2. Crea una variable `nombreAMostrar` (val).
3. Usa el operador Elvis para asignar a `nombreAMostrar` el valor de `usuario`. Si `usuario` es nulo, asigna "Invitado".
4. Imprime el resultado.
5. Cambia el valor de `usuario` a "Ana" y vuelve a ejecutar para ver la diferencia.

```kotlin
fun main() {
    var usuario: String? = null

    // TODO: Asigna usando el operador Elvis ?:
    val nombreAMostrar =

    println("Hola, $nombreAMostrar")
}
```

---

## Ejercicio 3: Llamada Segura (`?.`) y Propiedades

**Contexto:** Recibimos un mensaje de una API. El mensaje puede ser nulo. Si existe, queremos saber su longitud para mostrar un contador de caracteres en la UI.

1. Declara `mensaje: String? = null`.
2. Intenta acceder a `mensaje.length`. Verás que no te deja.
3. Usa la llamada segura `?.` para imprimir la longitud.
4. ¿Qué imprime la consola? (Debería ser `null`).
5. **Reto:** Combina `?.` y `?:` en una sola línea para que, si el mensaje es nulo, la longitud sea 0.

```kotlin
fun main() {
    val mensaje: String? = null

    // TODO: Imprime la longitud de forma segura. Si es null, que imprima 0.
    val longitud =

    println("Longitud del mensaje: $longitud")
}
```

---

## Ejercicio 4: La función `let`

**Contexto:** Queremos enviar un email de bienvenida **solo si** tenemos el correo del usuario. Si es nulo, no hacemos nada.

1. Tienes la variable `email: String?`.
2. Usa la función de alcance `.let {}` con el operador seguro `?.`.
3. Dentro del bloque `let`, imprime "Enviando correo a: [email]".
4. Prueba con `email = null` y con `email = "alumno@fp.es"`.

> **Pista:** Dentro del `let`, la variable se llama `it` y ya no es nulable (es `String`, no `String?`).

```kotlin
fun main() {
    val email: String? = "alumno@fp.es"

    // TODO: Usa ?.let { ... }

}
```

---

## Ejercicio 5: Listas y Nulos (Filtrado)

**Contexto:** Tienes un carrito de la compra. Por un error en la base de datos, algunos precios han llegado como `null`. Necesitamos calcular el total sumando solo los precios válidos.

1. Declara una lista: `val precios: List<Double?> = listOf(20.5, null, 10.0, null, 5.5)`.
2. Recorre la lista o utiliza funciones de colección para sumar solo los números.
3. **Pista Pro:** Busca información sobre la función `filterNotNull()`.

```kotlin
fun main() {
    val precios: List<Double?> = listOf(20.5, null, 10.0, null, 5.5)

    // TODO: Calcula la suma de los valores no nulos

    println("El total es: ...")
}
```

---

## Ejercicio 6: Simulación de UI State para Compose (¡Importante!)

**Contexto:** En Jetpack Compose, a menudo tenemos un estado que representa la carga de un perfil de usuario.

- Si el perfil es `null`, significa que "Cargando...".
- Si el perfil no es `null`, mostramos sus datos.

1. Crea una `data class Perfil(val nombre: String, val bio: String?)`. Nota que la biografía es opcional.
2. Crea una función `pintarPantalla(perfil: Perfil?)`.
3. Dentro de la función:
   - Si `perfil` es nulo, imprime "🔴 Cargando datos...".
   - Si no es nulo, imprime "🟢 Perfil de: ${perfil.nombre}".
   - Para la biografía: Si tiene bio, imprímela en mayúsculas. Si es nula, imprime "Sin descripción".

```kotlin
data class Perfil(val nombre: String, val bio: String?)

fun pintarPantalla(perfil: Perfil?) {
    // TODO: Implementar lógica de visualización
}

fun main() {
    // Caso 1: Aún no han llegado los datos
    pintarPantalla(null)

    println("---")

    // Caso 2: Llegan datos pero sin bio
    pintarPantalla(Perfil("Carlos", null))

    println("---")

    // Caso 3: Datos completos
    pintarPantalla(Perfil("Maria", "Programadora Kotlin experta"))
}
```

---

## ⚠️ Ejercicio 7: El operador Double-Bang (`!!`)

**Contexto:** A veces, los alumnos usan `!!` para que el compilador "se calle". Vamos a ver por qué es mala idea.

1. Crea una función que acepte un `String?`.
2. Dentro, aplica `!!.length` sobre la variable.
3. Llama a la función pasando `null`.
4. Observa la excepción en la consola. **Esta es la razón por la que tu App se cierra en el móvil del usuario.**

> **Reflexión:** ¿Cuándo estaría justificado usar `!!`? (Discutir en clase).

---

# 💡 Notas del Profesor para el Alumno

### ¿Por qué esto es vital para Jetpack Compose?

Cuando diseñéis interfaces en Compose, usaréis mucho código como este:

```kotlin
// Ejemplo real de Compose (No para ejecutar ahora, solo para leer)
var nombreUsuario by remember { mutableStateOf<String?>(null) }

if (nombreUsuario == null) {
    CircularProgressIndicator() // Muestra ruedita de carga
} else {
    Text(text = "Hola, $nombreUsuario") // Muestra el contenido
}
```

Si no entendéis bien la diferencia entre `String` y `String?`, tendréis errores constantes al intentar pasar parámetros a vuestros componentes `Text`, `Image` o `Button`.

**¡A programar sin miedo al Null!** 🚀
