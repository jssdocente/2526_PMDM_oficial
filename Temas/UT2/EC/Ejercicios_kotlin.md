# UT2. Ejercicios Kotlin nivel básicos/intermedios

### **📚 Guía de Ejercicios de Kotlin para Desarrolladores Java**

Kotlin el lenguaje que ha revolucionado el desarrollo en Android. Viniendo de Java, encontraréis muchas similitudes, pero también descubriréis un montón de características que harán vuestro código más seguro, conciso y expresivo.

El objetivo de estos ejercicios es afianzar los conceptos fundamentales de Kotlin a través de la práctica. ¡Vamos a programar!

### 🔧 Configuración del Entorno

Antes de empezar, vamos a configurar nuestro proyecto en Android Studio:

1.  Abre Android Studio y selecciona **"New Project"**.
2.  Elige la plantilla **"Empty Activity"**.
3.  Configura el proyecto con los siguientes datos:
    *   **Name**: `Ejercicios Kotlin`
    *   **Package name**: `com.pdmd.2526.T02.ejercicios_kotlin`
    *   **Language**: `Kotlin`
    *   **Minimum SDK**: Elige el que prefieras (ej. API 24).
4.  Haz clic en **"Finish"**.

Para cada temática, crearás un nuevo paquete y ficheros `.kt` tal y como se indicará en cada sección. ¡Empecemos!


### ⬆️ Entrega

- Comprimir todos los ficheros bajo la carpeta `com.pdmd.2526.T01.ejercicios_kotlin`
- Entregar en Moodle.

---

## 📚 1. Variables: `val` vs `var`

En Kotlin, la declaración de variables es explícita sobre su mutabilidad. A diferencia de Java, donde se usa `final` para las constantes, en Kotlin tenemos una distinción más clara y obligatoria desde el inicio.

*   `val` (de *value*): Se usa para declarar variables de solo lectura (inmutables). Una vez asignado un valor, no se puede cambiar. Es el equivalente al `final` en Java. **Se recomienda usar `val` siempre que sea posible.**
*   `var` (de *variable*): Se usa para declarar variables mutables, cuyo valor puede cambiar a lo largo del programa.

Esta distinción ayuda a escribir código más seguro y predecible.

🔗 **Documentación Oficial:** [Variables en Kotlin](https://kotlinlang.org/docs/basic-syntax.html#variables)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.valvar`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Declaración Básica**
    *   Declara una variable inmutable `nombre` con tu nombre.
    *   Declara una variable mutable `edad` con tu edad.
    *   Imprime ambas variables en la consola.

2.  **Ejercicio 2: Intento de Reasignación**
    *   Crea una variable `val` llamada `ciudadNatal` y asígnale tu ciudad.
    *   Intenta reasignar un nuevo valor a `ciudadNatal`.
    *   Observa el error que muestra el IDE. Comenta la línea que da error y explica en un comentario por qué ocurre.

3.  **Ejercicio 3: Inferencia de Tipos**
    *   Declara una `val` para un número entero y una `var` para un `String` sin especificar su tipo explícitamente.
    *   Kotlin debe inferir el tipo. Imprime el nombre de la clase de cada variable usando `::class.simpleName`.

4.  **Ejercicio 4: Tipos Explícitos**
    *   Declara una variable mutable `puntuacion` de tipo `Double`, pero inicialízala con un valor entero como `10`.
    *   Luego, reasigna a `puntuacion` un valor con decimales, como `15.5`.
    *   Imprime el valor final.

5.  **Ejercicio 5: `val` en Clases**
    *   Crea una clase `Usuario` con dos propiedades: un `val` para el `id` (un número entero) y un `var` para el `nombreDeUsuario`.
    *   Crea una instancia de `Usuario`, intenta modificar tanto el `id` como el `nombreDeUsuario`. ¿Qué sucede?

---

## 🛠️ 2. Funciones y Parámetros por Defecto

Las funciones en Kotlin se declaran con la palabra clave `fun`. Una de sus características más potentes, en comparación con Java, es la capacidad de definir **parámetros por defecto** y usar **argumentos nombrados** al llamar a la función. Esto reduce la necesidad de sobrecargar métodos.

```kotlin
fun saludar(nombre: String, mensaje: String = "¡Bienvenido!") {
    println("Hola, $nombre. $mensaje")
}

// Llamadas válidas:
saludar("Ana") // Usa el mensaje por defecto
saludar("Carlos", "¡Qué tal!") // Provee ambos parámetros
saludar(mensaje = "¿Cómo estás?", nombre = "Elena") // Argumentos nombrados
```

🔗 **Documentación Oficial:** [Funciones en Kotlin](https://kotlinlang.org/docs/functions.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.funciones`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Función Básica**
    *   Crea una función `imprimirNombre` que acepte un `String` (tu nombre) y lo imprima.

2.  **Ejercicio 2: Función con Valor de Retorno**
    *   Crea una función `obtenerCalificacion` que reciba un `Double` (nota de examen) y devuelva un `String`: "Aprobado" si la nota es >= 5.0 y "Suspendido" en caso contrario.

3.  **Ejercicio 3: Parámetros por Defecto**
    *   Crea una función `registrarUsuario` que acepte `nombre` (String) y `pais` (String). El país debe tener "España" como valor por defecto.
    *   Llama a la función una vez solo con el nombre y otra vez con ambos datos.

4.  **Ejercicio 4: Argumentos Nombrados**
    *   Define una función `crearNotificacion` con los parámetros: `usuario` (String), `mensaje` (String), `prioridad` (Int = 1).
    *   Llama a esta función proporcionando el `usuario` y la `prioridad`, pero no el `mensaje` (usa argumentos nombrados para omitir el del medio). Deberás asignar un valor por defecto también al mensaje.

5.  **Ejercicio 5: Función de una sola expresión**
    *   Reconvierte la función `obtenerCalificacion` del ejercicio 2 para que sea una función de una sola expresión (usando `=` en lugar de llaves y `return`).

---

## 🧩 3. Funciones de Extensión

Las funciones de extensión te permiten "añadir" nuevas funciones a clases existentes sin tener que heredar de ellas. Es una forma increíblemente poderosa de hacer tu código más legible y fluido. Por ejemplo, podrías añadir una función a la clase `String`.

```kotlin
// Añadimos la función "capitalizeWords" a la clase String
fun String.capitalizeWords(): String {
    return this.split(" ").joinToString(" ") { it.capitalize() }
}

val frase = "hola mundo kotlin"
println(frase.capitalizeWords()) // "Hola Mundo Kotlin"
```

🔗 **Documentación Oficial:** [Funciones de Extensión](https://kotlinlang.org/docs/extensions.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.extensiones`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Extensión para `Int`**
    *   Crea una función de extensión para la clase `Int` llamada `esPar()` que devuelva `true` si el número es par y `false` en caso contrario.

2.  **Ejercicio 2: Extensión para `String`**
    *   Crea una función de extensión para `String` llamada `gritando()` que convierta el texto a mayúsculas y le añada tres signos de exclamación al final.

3.  **Ejercicio 3: Extensión con Parámetros**
    *   Crea una función de extensión para la clase `String` llamada `limitar(longitud: Int)` que recorte el string a la longitud indicada y añada "..." al final si fue recortado.

4.  **Ejercicio 4: Extensión para una Lista**
    *   Crea una función de extensión para `List<Int>` llamada `producto()` que devuelva el producto de todos los números de la lista.

5.  **Ejercicio 5: Extensión para Clase Propia**
    *   Define una clase `Circulo(val radio: Double)`.
    *   Crea una función de extensión para `Circulo` llamada `calcularArea()` que devuelva su área (π * r²).

---

## 🤔 4. Expresiones vs. Sentencias

En Java, casi todo son sentencias (statements). En Kotlin, muchas construcciones, como `if`, `when` o `try`, son expresiones (expressions), lo que significa que devuelven un valor. Esto permite un código mucho más conciso.

```java
// Java (Sentencia)
int temp = 25;
String clima;
if (temp > 20) {
    clima = "Cálido";
} else {
    clima = "Fresco";
}
```

```kotlin
// Kotlin (Expresión)
val temp = 25
val clima = if (temp > 20) "Cálido" else "Fresco"
```

🔗 **Documentación Oficial:** [Control Flow](https://kotlinlang.org/docs/control-flow.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.expresiones`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: `if` como Expresión**
    *   Declara una variable `edad` con un valor.
    *   Usa una expresión `if` para asignar a una variable `categoria` el valor "Menor de edad" si `edad` es < 18, o "Mayor de edad" en caso contrario. Imprime `categoria`.

2.  **Ejercicio 2: `if` con Bloques**
    *   Asigna a una variable `descripcion` el resultado de una expresión `if`. Dentro de cada bloque (`if` y `else`), imprime un mensaje en consola y, en la última línea del bloque, devuelve el `String` que se asignará.

3.  **Ejercicio 3: `when` como Expresión**
    *   Crea una variable `diaSemana` (un `Int` de 1 a 7).
    *   Usa una expresión `when` para asignar a la variable `nombreDia` el nombre del día correspondiente ("Lunes", "Martes", etc.).

4.  **Ejercicio 4: Combinando Expresiones**
    *   Declara una variable `puntuacion` (0-100).
    *   Asigna a una variable `resultado` el valor de un `when` que devuelva: "Sobresaliente" (90-100), "Notable" (70-89), "Aprobado" (50-69) o "Suspendido" (<50).

5.  **Ejercicio 5: Expresión `try-catch`**
    *   Crea una función que reciba un `String`. Dentro, usa una expresión `try-catch` para convertir el `String` a `Int`. Si tiene éxito, devuelve el número. Si falla (`NumberFormatException`), devuelve `null`.

---

## 🔀 5. Operador `when`

El operador `when` es la evolución del `switch` de Java. Es mucho más potente y flexible.

*   No necesita `break`.
*   Puede combinar múltiples valores en una misma rama (con comas).
*   Puede usar rangos (`in 1..10`).
*   Puede comprobar tipos (`is String`).
*   Puede no tener argumento y usarse como una cadena de `if-else if`.
*   Su rama `else` es obligatoria cuando se usa como expresión, garantizando que siempre se devuelva un valor.

🔗 **Documentación Oficial:** [When Expression](https://kotlinlang.org/docs/control-flow.html#when-expression)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.whenoperator`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: `switch` Básico**
    *   Crea una función que reciba un `Int` representando un mes (1-12) y devuelva el nombre del mes usando `when`.

2.  **Ejercicio 2: Agrupando Casos**
    *   Crea una función que reciba un `Int` (mes) y devuelva la estación del año. Agrupa los meses (ej. 12, 1, 2 -> "Invierno").

3.  **Ejercicio 3: Usando Rangos**
    *   Crea una función que reciba una `Int` (edad) y devuelva una categoría: "Niño" (0-12), "Adolescente" (13-19), "Adulto" (20-64), "Senior" (65+).

4.  **Ejercicio 4: Comprobación de Tipos**
    *   Crea una función que reciba un parámetro de tipo `Any` (el `Object` de Kotlin). Usa `when` para determinar si es un `String`, un `Int`, un `Boolean` u otro tipo, e imprime un mensaje diferente para cada caso.

5.  **Ejercicio 5: `when` sin Argumento**
    *   Crea una función que reciba dos números, `a` y `b`. Usa un `when` sin argumento para devolver `"$a es mayor"`, `"$b es mayor"` o `"Son iguales"`.

---

## 🛡️ 6. Nulabilidad (Null Safety)

Kotlin resuelve el famoso "error de los mil millones de dólares" (NullPointerException) integrando la nulabilidad en su sistema de tipos.

*   **Tipos no nulos (por defecto):** `String`, `Int`. No pueden contener `null`.
*   **Tipos nulables (con `?`):** `String?`, `Int?`. Pueden contener un valor o `null`.

Para operar con tipos nulables de forma segura, Kotlin ofrece:
*   **Safe Call (`?.`):** Llama a un método solo si el objeto no es nulo. Si es nulo, toda la expresión devuelve `null`. `nombre?.length`
*   **Elvis Operator (`?:`):** Proporciona un valor por defecto si la expresión a su izquierda es nula. `val longitud = nombre?.length ?: 0`
*   **Not-null assertion (`!!`):** Convierte un tipo nulable a no nulo, pero lanza una `KotlinNullPointerException` si el valor es `null`. **¡Úsalo con mucho cuidado!**

🔗 **Documentación Oficial:** [Null Safety](https://kotlinlang.org/docs/null-safety.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.nullsafety`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Declaración Nulable**
    *   Declara una variable `nombre` de tipo `String` y otra `apellido` de tipo `String?`.
    *   Asigna valores a ambas. Luego, intenta asignar `null` a `nombre`. Observa el error y coméntalo. Asigna `null` a `apellido`.

2.  **Ejercicio 2: Safe Call (`?.`)**
    *   Crea una función que reciba un `String?` y devuelva su longitud. Si el string es `null`, la función debe devolver `null`. Usa el operador de llamada segura.

3.  **Ejercicio 3: Elvis Operator (`?:`)**
    *   Modifica la función anterior para que, si el string es `null`, devuelva `0` en lugar de `null`. Usa el operador Elvis.

4.  **Ejercicio 4: Combinando Operadores**
    *   Crea una clase `Usuario` con una propiedad nulable `direccion` de tipo `String?`.
    *   Crea una lista de `Usuario?`.
    *   Recorre la lista e imprime la dirección de cada usuario. Si el usuario o la dirección son nulos, imprime "Dirección desconocida".

5.  **Ejercicio 5: Not-null Assertion (`!!`)**
    *   Crea una función que acepte un `String?`. Dentro, asegúrale al compilador que no es nulo usando `!!` e imprime su longitud. Luego, llama a la función pasándole `null` para ver cómo se produce la excepción. **Este ejercicio es para entender el riesgo.**

6.  **Ejercicio 6: `let` para Nulos**
    *   Crea una variable `email` de tipo `String?`.
    *   Usa la función de scope `let` (`email?.let { ... }`) para ejecutar un bloque de código solo si `email` no es nulo. Dentro del bloque, imprime el email.

---

## 🗃️ 7. Colecciones (List, Set, Map)

Kotlin ofrece una rica API para trabajar con colecciones, distinguiendo entre interfaces **inmutables** (`List`, `Set`, `Map`) y **mutables** (`MutableList`, `MutableSet`, `MutableMap`).

*   **List:** Colección ordenada, permite duplicados.
*   **Set:** Colección sin duplicados, generalmente desordenada.
*   **Map:** Colección de pares clave-valor, las claves son únicas.

Las funciones de orden superior como `map`, `filter`, `forEach` o `groupBy` hacen que la manipulación de colecciones sea extremadamente concisa y legible.

🔗 **Documentación Oficial:** [Colecciones en Kotlin](https://kotlinlang.org/docs/collections-overview.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.colecciones`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Lista Inmutable**
    *   Crea una lista inmutable de `String` con nombres de ciudades.
    *   Recórrela con un `forEach` e imprime cada ciudad.
    *   Intenta añadir una nueva ciudad y observa el error.

2.  **Ejercicio 2: Lista Mutable**
    *   Crea una lista mutable de `Int`.
    *   Añade 5 números.
    *   Elimina uno de ellos por su valor.
    *   Modifica el valor de un elemento por su índice.
    *   Imprime la lista final.

3.  **Ejercicio 3: `filter` y `map`**
    *   Dada una lista de números `(1..10).toList()`, usa `filter` para obtener solo los números pares.
    *   Luego, encadena una llamada a `map` para multiplicar cada número par por 10.
    *   Imprime el resultado.

4.  **Ejercicio 4: `Set` para valores únicos**
    *   Crea una lista mutable con nombres de personas, incluyendo algunos duplicados.
    *   Conviértela a un `Set` para eliminar los duplicados e imprime el resultado.

5.  **Ejercicio 5: `Map` Inmutable**
    *   Crea un mapa inmutable donde las claves sean nombres de países (`String`) y los valores sus capitales (`String`).
    *   Pide al mapa la capital de un país e imprímela.
    *   Recorre el mapa e imprime "La capital de [país] es [capital]".

6.  **Ejercicio 6: `Map` Mutable**
    *   Crea un mapa mutable que almacene las puntuaciones de un juego (jugador: `String`, puntos: `Int`).
    *   Añade 3 jugadores con sus puntuaciones.
    *   Actualiza la puntuación de un jugador existente.
    *   Imprime el mapa final.

7.  **Ejercicio 7: `groupBy`**
    *   Crea una lista de `String` con varias palabras. Usa `groupBy` para agrupar las palabras por su primera letra. El resultado será un `Map<Char, List<String>>`. Imprímelo.

---

## 🏛️ 8. Programación Orientada a Objetos (POO)

Kotlin simplifica mucho la POO comparado con Java.

*   **Constructores primarios:** Se declaran en la propia firma de la clase. Las propiedades (`val` o `var`) pueden declararse directamente en él.
*   **Herencia:** Se usa `:` en lugar de `extends`. Las clases son `final` por defecto, por lo que deben marcarse como `open` para poder heredar de ellas. Los métodos también deben ser `open` para poder ser sobreescritos (`override`).

```kotlin
// Clase base (debe ser open)
open class Vehiculo(val marca: String) {
    open fun acelerar() { // Método (debe ser open)
        println("Acelerando vehículo genérico")
    }
}

// Clase derivada
class Coche(marca: String, val modelo: String) : Vehiculo(marca) {
    override fun acelerar() { // Sobrescritura
        println("Acelerando el coche $marca $modelo")
    }
}
```

🔗 **Documentación Oficial:** [Clases y Herencia](https://kotlinlang.org/docs/classes.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.poo`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Clase Simple**
    *   Crea una clase `Persona` con un constructor primario que acepte `nombre` (val, String) y `edad` (var, Int).

2.  **Ejercicio 2: Métodos en una Clase**
    *   Añade un método `cumplirAnio()` a la clase `Persona` que incremente la edad en 1.
    *   Añade un método `presentarse()` que imprima "Hola, soy [nombre] y tengo [edad] años."

3.  **Ejercicio 3: Herencia Básica**
    *   Crea una clase `open class Empleado` con propiedades `nombre` y `salario`.
    *   Crea una clase `Gerente` que herede de `Empleado` y añada una propiedad `departamento`.
    *   Asegúrate de llamar al constructor de la clase padre correctamente.

4.  **Ejercicio 4: Sobrescritura de Métodos**
    *   Añade un método `open fun calcularBonus()` en `Empleado` que devuelva el 10% del salario.
    *   Sobrescribe `calcularBonus()` en `Gerente` para que devuelva el 20% del salario más un extra de 500.

5.  **Ejercicio 5: Bloque `init`**
    *   En la clase `Persona`, añade un bloque `init` que imprima un mensaje cada vez que se cree una nueva instancia.

---

##  Singleton: `object` vs `class`

En Java, para crear un Singleton, necesitas un constructor privado, una instancia estática y un método `getInstance()`. En Kotlin, es mucho más simple: solo usa la palabra clave `object`.

*   `class`: Es una plantilla para crear múltiples objetos (instancias).
*   `object`: Declara una clase y crea una única instancia de ella al mismo tiempo. Es un Singleton por naturaleza. Es ideal para gestores, configuraciones globales, etc.

```kotlin
object GestorDeConexion {
    fun conectar() { /* ... */ }
    fun desconectar() { /* ... */ }
}

// Uso:
GestorDeConexion.conectar()
```

🔗 **Documentación Oficial:** [Object Declarations](https://kotlinlang.org/docs/object-declarations.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.objects`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Singleton Básico**
    *   Crea un `object` llamado `ConfiguracionApp` que contenga propiedades inmutables como `version` (String) y `entorno` (String, ej. "Producción").
    *   Accede a estas propiedades desde otra parte de tu código e imprímelas.

2.  **Ejercicio 2: Singleton con Métodos**
    *   Crea un `object` `Logger` con un método `log(mensaje: String)` que imprima el mensaje precedido de una marca de tiempo.

3.  **Ejercicio 3: Comparación `object` y `class`**
    *   Crea una `class` `ContadorClass` con un método `incrementar()`.
    *   Crea un `object` `ContadorObject` con un método `incrementar()`.
    *   Crea dos instancias de `ContadorClass` e llama a `incrementar` en ambas. ¿Comparten el estado?
    *   Llama a `incrementar` varias veces en `ContadorObject`. ¿Qué ocurre con su estado? Explica la diferencia en comentarios.

---

## ☕ 10. `static` y `companion object`

Kotlin no tiene la palabra clave `static`. Para lograr una funcionalidad similar (métodos o propiedades que pertenecen a la clase y no a la instancia), se usa un **`companion object`**.

Un `companion object` es un `object` singleton que está ligado a una clase. Sus miembros se pueden llamar usando el nombre de la clase, igual que los miembros estáticos en Java.

```java
// Java
public class Utils {
    public static final String TAG = "MyApp";
    public static int count(String text) {
        return text.length();
    }
}
```

```kotlin
// Kotlin
class MiClase {
    companion object {
        const val TAG = "MyApp" // `const` es para constantes de compilación
        fun crear(): MiClase = MiClase()
    }
}

// Uso:
println(MiClase.TAG)
val instancia = MiClase.crear()
```

🔗 **Documentación Oficial:** [Companion Objects](https://kotlinlang.org/docs/object-declarations.html#companion-objects)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.companion`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Constantes de Clase**
    *   Crea una clase `Matematicas` y, dentro de un `companion object`, define una constante `PI` con el valor 3.14159.
    *   Accede a ella como `Matematicas.PI`.

2.  **Ejercicio 2: Métodos de Fábrica (Factory Methods)**
    *   Crea una clase `Usuario` con un constructor privado.
    *   Dentro de su `companion object`, crea dos funciones: `crearUsuarioNormal(nombre: String)` y `crearUsuarioAdmin(nombre: String)`, que devuelvan instancias de `Usuario` con diferentes configuraciones internas.

3.  **Ejercicio 3: Implementando Interfaces**
    *   Crea una interfaz `JsonFactory<T>` con un método `fromJson(json: String): T`.
    *   Haz que el `companion object` de una clase `Persona` implemente esta interfaz para poder crear una `Persona` a partir de un `String` JSON simulado.

---

## 📝 11. Data Classes

Las `data class` son una de las características más queridas de Kotlin. Son clases diseñadas para contener datos. El compilador genera automáticamente:

*   `equals()` y `hashCode()` (basado en las propiedades del constructor primario).
*   `toString()` (con un formato legible).
*   `copy()` (para crear copias de la instancia, opcionalmente modificando algunas propiedades).
*   `componentN()` (funciones para desestructuración).

Esto elimina una enorme cantidad de código repetitivo (boilerplate) que es común en Java.

🔗 **Documentación Oficial:** [Data Classes](https://kotlinlang.org/docs/data-classes.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.dataclasses`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Creación y `toString()`**
    *   Crea una `data class` `Libro` con `titulo` (String), `autor` (String) e `isbn` (String).
    *   Crea una instancia e imprímela. Observa la salida legible que genera `toString()`.

2.  **Ejercicio 2: `equals()` y `hashCode()`**
    *   Crea dos instancias de `Libro` con los mismos datos.
    *   Compara si son iguales usando `==`. ¿Qué resultado esperas?
    *   Crea una tercera instancia con datos diferentes y compárala con la primera.

3.  **Ejercicio 3: `copy()`**
    *   Crea una instancia de `Libro`.
    *   Usa la función `copy()` para crear una nueva instancia que sea idéntica pero con un `titulo` diferente.
    *   Imprime ambas instancias.

4.  **Ejercicio 4: Desestructuración**
    *   Crea una instancia de `Libro`.
    *   Usa la declaración de desestructuración para extraer sus propiedades en tres variables separadas: `val (miTitulo, miAutor, miIsbn) = libro`.
    *   Imprime las tres variables nuevas.

---

## 🎨 12. Enum Classes

Las `enum class` en Kotlin son mucho más potentes que en Java. No solo son listas de constantes, sino que pueden tener propiedades, métodos e implementar interfaces.

```kotlin
enum class Prioridad(val color: String) {
    BAJA("#00FF00"),
    MEDIA("#FFFF00") {
        override fun notificar() {
            println("Notificación de prioridad media.")
        }
    },
    ALTA("#FF0000");

    open fun notificar() {
        println("Notificación de prioridad estándar.")
    }
}
```

🔗 **Documentación Oficial:** [Enum Classes](https://kotlinlang.org/docs/enum-classes.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.enums`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Enum Básico**
    *   Crea una `enum class` `DiaDeLaSemana` con los días de lunes a domingo.
    *   Crea una función que reciba un `DiaDeLaSemana` y use `when` para devolver si es "Fin de semana" o "Día laborable".

2.  **Ejercicio 2: Enum con Propiedades**
    *   Crea una `enum class` `EstadoPedido` con los valores `PROCESANDO`, `ENVIADO`, `ENTREGADO`.
    *   Añádele una propiedad `descripcion` (String) a cada estado.

3.  **Ejercicio 3: Enum con Métodos**
    *   Crea una `enum class` `OperacionMatematica` con valores `SUMA`, `RESTA`, `MULTIPLICACION`.
    *   Añade un método `calcular(a: Int, b: Int): Int` que realice la operación correspondiente.

4.  **Ejercicio 4: Iterando un Enum**
    *   Usa el método `values()` de tu `enum class` `DiaDeLaSemana` para iterar sobre todos sus valores e imprimirlos.

---

## 람 13. Funciones Lambda

Las lambdas son funciones anónimas que puedes tratar como valores: pasarlas como argumentos, devolverlas o almacenarlas en variables. Son la base de la programación funcional en Kotlin.

La sintaxis es `{ parámetros -> cuerpo }`.

```kotlin
val suma: (Int, Int) -> Int = { a, b -> a + b }
val resultado = suma(5, 3) // resultado es 8
```

Son extremadamente comunes en operaciones con colecciones.

🔗 **Documentación Oficial:** [Higher-Order Functions and Lambdas](https://kotlinlang.org/docs/lambdas.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.lambdas`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Lambda en una Variable**
    *   Crea una variable `saludo` que contenga una lambda que no reciba parámetros y devuelva el `String` "Hola desde una lambda!". Llama a la lambda e imprime su resultado.

2.  **Ejercicio 2: Lambda con Parámetros**
    *   Crea una lambda `cuadrado` que acepte un `Int` y devuelva su cuadrado. Pruébala.

3.  **Ejercicio 3: Lambda en `forEach`**
    *   Dada una lista de nombres, usa `forEach` con una lambda para imprimir cada nombre en mayúsculas.

4.  **Ejercicio 4: Función de Orden Superior**
    *   Crea una función `operar(a: Int, b: Int, operacion: (Int, Int) -> Int): Int`. Esta función debe aplicar la lambda `operacion` a los números `a` y `b`.
    *   Llámala pasándole una lambda para la suma.

5.  **Ejercicio 5: `it` para un solo parámetro**
    *   Dada una lista de números, usa la función `count` con una lambda para contar cuántos números son mayores que 5. Usa el parámetro implícito `it`.

---

## 💎 14. Genéricos

Los genéricos, al igual que en Java, permiten escribir clases y funciones que pueden trabajar con cualquier tipo, proporcionando seguridad de tipos en tiempo de compilación. La sintaxis es muy similar.

```kotlin
// Función genérica
fun <T> imprimirElemento(elemento: T) {
    println(elemento)
}

// Clase genérica
class Caja<T>(var contenido: T) {
    fun obtenerContenido(): T = contenido
}
```

🔗 **Documentación Oficial:** [Generics](https://kotlinlang.org/docs/generics.html)

---

- 📦 **Paquete:** `com.pdmd.2526.T01.ejercicios_kotlin.genericos`
- 📝 **Ficheros:** `ejercicio_1.kt`, `ejercicio_2.kt`, ...

### Ejercicios Propuestos:

1.  **Ejercicio 1: Función Genérica**
    *   Crea una función genérica `primerElemento` que reciba una `List<T>` y devuelva el primer elemento, o `null` si la lista está vacía.

2.  **Ejercicio 2: Clase Genérica**
    *   Crea una clase genérica `Par<A, B>` que almacene un par de valores de tipos posiblemente diferentes.

3.  **Ejercicio 3: Restricciones de Tipo (Type Constraints)**
    *   Crea una función genérica `ordenarLista` que solo acepte listas de elementos que implementen la interfaz `Comparable<T>`. Debe devolver la lista ordenada.

4.  **Ejercicio 4: `out` para Covarianza**
    *   Crea una interfaz genérica `Fuente<out T>` con un método `proximo(): T`. Explica en un comentario por qué `out` es adecuado aquí (indica que `T` solo se produce, no se consume).

5.  **Ejercicio 5: `in` para Contravarianza**
    *   Crea una interfaz genérica `Consumidor<in T>` con un método `consumir(item: T)`. Explica por qué `in` es adecuado (indica que `T` solo se consume).

---


