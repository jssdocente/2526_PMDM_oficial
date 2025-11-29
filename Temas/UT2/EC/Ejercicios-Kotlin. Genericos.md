# Ejercicios: Genéricos en Kotlin

## Ejercicio 1: Almacén Genérico Simple

Imaginad que necesitáis un almacén que pueda guardar cualquier tipo de objeto (productos, clientes, pedidos, etc.).

**Tarea:**

1.  Crea una clase genérica llamada `Almacen<T>` con las siguientes características:
    - Debe tener una lista mutable interna (`private val items: MutableList<T>`) para almacenar los objetos.
    - Un método `añadirItem(item: T)` que añada un objeto a la lista.
    - Un método `obtenerItem(index: Int): T?` que devuelva un objeto por su índice, o `null` si el índice no es válido.
    - Un método `listarItems()` que imprima todos los objetos almacenados.
    - Un método `contarItems(): Int` que devuelva el número de objetos en el almacén.
2.  En la función `main`, crea dos instancias de `Almacen`:
    - Una para almacenar `String` (ej. nombres de productos).
    - Otra para almacenar `Int` (ej. números de pedido).
3.  Añade algunos elementos a cada almacén, lista sus contenidos y obtén un elemento por índice.

**Pista:** Pensad cómo Compose podría usar un `Almacen<MyCustomObject>` para gestionar una lista de elementos que se muestran en un `LazyColumn`.

## Ejercicio 2: Procesador de Datos Numéricos (Restricciones de Tipo)

Necesitáis una función que pueda calcular el promedio de una lista de números, ya sean enteros, dobles, etc.

**Tarea:**

1.  Crea una función genérica llamada `calcularPromedio<T>` que acepte una `List<T>` como parámetro.
2.  Aplica una restricción de tipo para asegurar que `T` sea un subtipo de `Number`. Esto os permitirá llamar a métodos como `toDouble()` en los elementos de la lista.
3.  La función debe devolver el promedio de los números como un `Double`. Si la lista está vacía, debe devolver `0.0`.
4.  En la función `main`, prueba la función `calcularPromedio` con:
    - Una `List<Int>`.
    - Una `List<Double>`.
    - Intentad pasar una `List<String>` y observad el error de compilación.

**Pista:** La restricción `T : Number` es clave para poder realizar operaciones matemáticas en `T`.

## Ejercicio 3: Intercambiador Universal (Funciones Genéricas)

Diseñad una función que pueda intercambiar dos elementos en cualquier tipo de `Array`.

**Tarea:**

1.  Crea una función genérica `intercambiarElementos<T>(array: Array<T>, index1: Int, index2: Int): Unit`.
2.  La función debe intercambiar los elementos en las posiciones `index1` y `index2` dentro del `array`.
3.  Asegúrate de incluir una validación para los índices para evitar `IndexOutOfBoundsException`. Si los índices no son válidos, imprime un mensaje de error y no hagas nada.
4.  En la función `main`, prueba la función `intercambiarElementos` con:
    - Un `Array<String>`.
    - Un `Array<Boolean>`.
    - Un `Array<Char>`.
    - Prueba también con índices inválidos.

**Pista:** Las funciones genéricas son perfectas para operaciones de utilidad que son aplicables a muchos tipos de datos.

## Ejercicio 4: Filtro de Colecciones con Predicado (Funciones de Extensión Genéricas)

Queréis una forma general de filtrar elementos de cualquier colección basándose en una condición.

**Tarea:**

1.  Crea una función de extensión genérica para `List<T>` llamada `filtrarPorCondicion<T>(predicado: (T) -> Boolean): List<T>`.
2.  La función debe devolver una nueva lista que contenga solo los elementos de la lista original para los cuales el `predicado` (una función lambda) devuelve `true`.
3.  En la función `main`, prueba `filtrarPorCondicion` con:
    - Una `List<Int>` para filtrar números pares.
    - Una `List<String>` para filtrar cadenas que empiecen por "A".
    - Una `List<Persona>` (definid una `data class Persona(val nombre: String, val edad: Int)`) para filtrar personas mayores de 30 años.

**Pista:** Esta es una forma muy común de usar genéricos en Kotlin y se parece mucho a las funciones de orden superior en las colecciones estándar.

## Ejercicio 5: Proveedor y Consumidor (Varianza - `in` y `out`)

Este ejercicio explora la varianza, que es un concepto más avanzado pero crucial para entender cómo funcionan las interfaces genéricas de Kotlin.

**Tarea:**

1.  Define una clase base `Animal` y dos subclases: `Perro` y `Gato`.
2.  Define una interfaz `abstract` genérica `Proveedor<out T>` con un método `obtener(): T`.
    - Crea una implementación concreta `ProveedorPerros` que devuelva un `Perro`.
3.  Define una interfaz `abstract` genérica `Consumidor<in T>` con un método `procesar(item: T)`.
    - Crea una implementación concreta `ConsumidorAnimales` que procese cualquier `Animal`.
4.  En la función `main`, demuestra la varianza:
    - Para `Proveedor`: Declara una variable `val proveedorAnimal: Proveedor<Animal>` y asígnale una instancia de `ProveedorPerros`. Llama a `obtener()` y verifica que devuelve un `Animal` (que de hecho es un `Perro`). Esto es posible gracias a `out`.
    - Para `Consumidor`: Declara una variable `val consumidorPerros: Consumidor<Perro>` y asígnale una instancia de `ConsumidorAnimales`. Llama a `procesar()` con un `Perro`. Esto es posible gracias a `in`.
    - Intenta hacer las asignaciones inversas (ej. `Proveedor<Perro> = Proveedor<Animal>`) y observa los errores de compilación.

**Pista:** Pensad en `out` como "solo producir" y `in` como "solo consumir". Si una clase genérica solo produce elementos de tipo `T`, puede ser `out`. Si solo consume, puede ser `in`.

---

¡Estos ejercicios os ayudarán a dominar los genéricos! Recordad que la práctica es clave. Si os quedáis atascados, no dudéis en revisar los ejemplos resueltos o las explicaciones. ¡A programar!
