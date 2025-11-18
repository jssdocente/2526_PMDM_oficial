# Ejercicios de Kotlin: Funciones, Enumerados y Clases

## Bloque 1: Funciones con Valores por Defecto

En este bloque, practicaremos la creación de funciones que aprovechan los valores por defecto para hacerlas más flexibles y versátiles.

### Ejercicio F1: Saludo Personalizado

Crea una función llamada `saludarPersonalizado` que:

- Reciba un `nombre` (String, obligatorio).
- Reciba un `mensaje` (String), con un valor por defecto de `"¡Hola!"`.

La función debe imprimir el mensaje de saludo completo por consola.

```kotlin
// Implementa la función saludarPersonalizado aquí:
```

### Ejercicio F2: Calcular Coste de Compra

Crea una función llamada `calcularCosteCompra` que:

- Reciba un `precioProducto` (Double, obligatorio).
- Reciba un `esClientePremium` (Boolean), con valor por defecto `false`.
- Reciba un `descuentoAdicional` (Double), con valor por defecto `0.0`.

Consideraciones:

- Si `esClientePremium` es `true`, el envío es GRATIS. Si no, el coste de envío es de `3.50€`.
- El `descuentoAdicional` se aplica al `precioProducto` (por ejemplo, un 0.10 para un 10% de descuento).
- La función debe devolver el `Double` del coste final total.

```kotlin
// Implementa la función calcularCosteCompra aquí:
```

### Ejercicio F3: Configurar Alarma

Crea una función llamada `configurarAlarma` que:

- Reciba `hora` (Int, obligatorio) y `minuto` (Int, obligatorio).
- Reciba `repetir` (Boolean), con valor por defecto `false`.
- Reciba `sonido` (String), con valor por defecto `"Predeterminado"`.
- Reciba `mensaje` (String), con valor por defecto vacío (`""`).

La función debe imprimir un resumen de cómo se configuró la alarma.

**Ejemplo de salidas esperadas:**

- `Alarma configurada para las 7:30. Sonido: Predeterminado. Repetir: No. Mensaje: Sin mensaje.`
- `Alarma configurada para las 9:00. Sonido: Campana. Repetir: Sí. Mensaje: Es hora del café.`

```kotlin
// Implementa la función configurarAlarma aquí:
```

---

## Bloque 2: Enumerados

En este bloque, exploraremos cómo utilizar los enumerados para representar conjuntos fijos de valores y asociarles propiedades o comportamientos.

### Ejercicio E1: Estado de Conexión

Crea un enum llamado `EstadoConexion` con los siguientes estados:

- `CONECTADO`
- `DESCONECTADO`
- `RECONECTANDO`
- `PERDIDA`

Luego, crea una función `mostrarMensajeEstado` que reciba un `EstadoConexion` y use una expresión `when` para imprimir un mensaje descriptivo para cada estado.

```kotlin
// Implementa el enum EstadoConexion y la función mostrarMensajeEstado aquí:
```

### Ejercicio E2: Tipo de Pantalla

Crea un enum llamado `TipoPantalla` que represente diferentes pantallas de una aplicación. Cada tipo de pantalla debe tener las siguientes propiedades:

- `titulo` (String)
- `iconoUrl` (String)

Los tipos de pantalla deben ser:

- `INICIO` ("Inicio", "url_icono_home.png")
- `PERFIL` ("Mi Perfil", "url_icono_perfil.png")
- `AJUSTES` ("Configuración", "url_icono_settings.png")

Luego, crea una función `mostrarDetallesPantalla` que reciba un `TipoPantalla` e imprima su título y la URL del icono.

```kotlin
// Implementa el enum TipoPantalla y la función mostrarDetallesPantalla aquí:
```

### Ejercicio E3: Operación Matemática

Crea un enum llamado `OperacionMatematica` que defina las operaciones básicas:

- `SUMA`
- `RESTA`
- `MULTIPLICACION`
- `DIVISION`

Cada operación debe tener un método abstracto `realizar(a: Int, b: Int): Int` que implemente la lógica de su operación. (Puedes manejar la división por cero lanzando una excepción o asumiendo que no ocurrirá para simplificar).

Luego, crea una función `gestionarOperacion` que reciba un `OperacionMatematica` e imprima un mensaje antes de llamar a su método `realizar` con dos números de ejemplo (ej. `10` y `5`), e imprima el resultado.

```kotlin
// Implementa el enum OperacionMatematica y la función gestionarOperacion aquí:
```

---

## Bloque 3: Clases

En este bloque, trabajaremos con la creación de clases, explorando diferentes estilos de constructores, getters/setters, propiedades calculadas y `data class`.

### Ejercicio C1: Cuenta Bancaria (Estilo Java)

Crea una clase `CuentaBancaria` que siga un estilo similar a Java:

- **Propiedades privadas:** `numeroCuenta` (String, `val`), `titular` (String, `val`), `saldo` (Double, `var`).
- **Constructor:** Debe inicializar `numeroCuenta` y `titular`. `saldo` debe inicializarse a `0.0`.
- **Getters manuales:** Para todas las propiedades (`getNumeroCuenta()`, `getTitular()`, `getSaldo()`).
- **Métodos:**
  - `depositar(cantidad: Double)`: Añade la cantidad al saldo.
  - `retirar(cantidad: Double)`: Resta la cantidad del saldo, asegurándose de que no sea negativa.

```kotlin
// Implementa la clase CuentaBancaria aquí:
```

### Ejercicio C2: Producto de Tienda (Estilo Kotlin Conciso)

Crea una clase `ProductoTienda` usando la sintaxis concisa de Kotlin:

- **Propiedades en el constructor primario:** `nombre` (String, `val`), `precio` (Double, `var`).
- La clase debe tener un método `obtenerDescripcion()` que devuelva un String como:
  `"Producto: [nombre], Precio: [precio]€"`.

```kotlin
// Implementa la clase ProductoTienda aquí:
```

### Ejercicio C3: Cuadrado (Clase con Propiedades Calculadas)

Crea una clase `Cuadrado` que:

- Tenga una propiedad mutable `lado` (Double, `var`).
- Tenga dos propiedades calculadas: `area` (Double) y `perimetro` (Double).
  Estas propiedades deben calcularse automáticamente a partir del valor de `lado` cada vez que se acceda a ellas.

```kotlin
// Implementa la clase Cuadrado aquí:
```

### Ejercicio C4: Pelicula (Clase con Constructores Flexibles)

Crea una clase `Pelicula` que:

- Tenga un **constructor primario** con `titulo` (String, `val`), `director` (String, `val`, con valor por defecto `"Desconocido"`) y `anio` (Int, `val`, con valor por defecto `0`).
- Tenga un **constructor secundario** que solo reciba `titulo` y `anio`, y delegue al constructor primario usando `"Director Anónimo"` como director por defecto.

```kotlin
// Implementa la clase Pelicula aquí:
```

### Ejercicio C5: Evento (Data Class)

Crea una `data class` llamada `Evento` que represente un evento. Debe tener las siguientes propiedades:

- `nombre` (String, `val`)
- `lugar` (String, `val`)
- `fecha` (String, `val`)

La clase debe incluir un método `obtenerDetalles()` que retorne un String como:
`"El evento '[nombre]' será en '[lugar]' el '[fecha]'"`.

```kotlin
// Implementa la data class Evento aquí:
```
