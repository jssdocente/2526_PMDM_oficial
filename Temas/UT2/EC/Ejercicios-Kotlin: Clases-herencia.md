# Ejercicios: Herencia e Interfaces en Kotlin

## Ejercicio 1: Dispositivos Electrónicos (Herencia Básica)

Imaginad que estáis desarrollando un sistema para gestionar diferentes tipos de dispositivos electrónicos.

**Tarea:**

1.  Crea una clase `open` llamada `DispositivoElectronico` con las siguientes propiedades:
    - `nombre: String`
    - `marca: String`
    - `estaEncendido: Boolean` (inicializada a `false`)
    - Un método `encender()` que cambie `estaEncendido` a `true` e imprima "\[Nombre] de \[Marca] encendido."
    - Un método `apagar()` que cambie `estaEncendido` a `false` e imprima "\[Nombre] de \[Marca] apagado."
    - Un método `obtenerEstado()` que imprima si el dispositivo está encendido o apagado.
2.  Crea una subclase `Smartphone` que herede de `DispositivoElectronico`. Añade una propiedad `sistemaOperativo: String` y sobrescribe el método `obtenerEstado()` para incluir también el sistema operativo.
3.  Crea otra subclase `OrdenadorPortatil` que herede de `DispositivoElectronico`. Añade una propiedad `ramGB: Int` y un método `ejecutarPrograma(nombrePrograma: String)` que solo funcione si el ordenador está encendido.
4.  En la función `main`, crea instancias de `Smartphone` y `OrdenadorPortatil`, y realiza algunas operaciones con ellos (encender, apagar, obtener estado, ejecutar programa).

**Pista:** Recordad la palabra clave `open` para permitir la herencia y `override` para sobrescribir métodos.

## Ejercicio 2: Personajes de un Juego (Clases Abstractas)

Estáis diseñando un pequeño juego y queréis tener diferentes tipos de personajes que compartan algunas características pero tengan habilidades únicas.

**Tarea:**

1.  Crea una clase `abstract` llamada `Personaje` con las siguientes propiedades:
    - `nombre: String`
    - `salud: Int` (inicializada a 100)
    - `ataque: Int`
    - Un método `open fun recibirDano(cantidad: Int)` que reduzca la salud y compruebe si el personaje ha sido derrotado (salud <= 0). Imprime mensajes apropiados.
    - Una función `abstract fun atacar(enemigo: Personaje)` que debe ser implementada por las subclases.
    - Un método `fun mostrarEstado()` que imprima el nombre, salud y ataque del personaje.
2.  Crea una subclase concreta `Guerrero` que herede de `Personaje`. Implementa el método `atacar()` para que cause daño al enemigo basándose en su propio valor de `ataque`.
3.  Crea una subclase concreta `Mago` que herede de `Personaje`. Implementa el método `atacar()` para que cause daño al enemigo, pero con un mensaje diferente (ej. "\[Nombre] lanza un hechizo a \[Enemigo]!").
4.  En la función `main`, crea un `Guerrero` y un `Mago`. Haz que se ataquen mutuamente hasta que uno sea derrotado. Muestra el estado de los personajes en cada ronda.

**Pista:** Las clases abstractas son ideales para definir una base común con métodos que deben ser personalizados por cada tipo específico.

## Ejercicio 3: Objetos Interactuables (Interfaces)

En una aplicación, puede haber muchos objetos diferentes que pueden ser interactuados de ciertas maneras (ej. se les puede hacer clic, se les puede arrastrar, se pueden guardar).

**Tarea:**

1.  Define una interfaz llamada `Clickable` con un método `onClick()`.
2.  Define una interfaz llamada `Draggable` con un método `onDragStart()` y `onDragEnd()`.
3.  Crea una clase `BotonUI` (no es necesario que herede de nada) con una propiedad `texto: String`. Haz que esta clase implemente la interfaz `Clickable` e imprime un mensaje cuando se haga clic en ella.
4.  Crea una clase `VentanaUI` con una propiedad `titulo: String`. Haz que esta clase implemente la interfaz `Draggable` e imprime mensajes cuando se inicie y finalice el arrastre.
5.  Crea una clase `ImagenArrastrable` con propiedades `url: String` y `descripcion: String`. Haz que esta clase implemente **ambas** interfaces `Clickable` y `Draggable`. Implementa ambos comportamientos.
6.  En la función `main`, crea instancias de `BotonUI`, `VentanaUI` e `ImagenArrastrable` y simula sus interacciones llamando a sus métodos de interfaz.

**Pista:** Las interfaces permiten que clases de diferentes jerarquías compartan comportamientos comunes sin necesidad de herencia directa.

## Ejercicio 4: Sistema de Notificaciones (Herencia e Interfaces)

Construid un sistema de notificaciones donde podáis enviar mensajes a diferentes destinos (email, SMS).

**Tarea:**

1.  Define una interfaz `Notificable` con un método `enviarNotificacion(mensaje: String)`.
2.  Crea una clase `open` abstracta llamada `GestorNotificaciones` con una propiedad `nombreGestor: String`. Debe tener un método `abstract fun configurarCanal()`.
3.  Crea una subclase `GestorEmail` que herede de `GestorNotificaciones` e implemente `Notificable`.
    - Su método `configurarCanal()` debe imprimir "Configurando gestor de email...".
    - Su método `enviarNotificacion()` debe imprimir "Enviando email: \[mensaje]".
4.  Crea una subclase `GestorSMS` que herede de `GestorNotificaciones` e implemente `Notificable`.
    - Su método `configurarCanal()` debe imprimir "Configurando gestor de SMS...".
    - Su método `enviarNotificacion()` debe imprimir "Enviando SMS: \[mensaje]".
5.  En la función `main`, crea instancias de `GestorEmail` y `GestorSMS`. Llama a `configurarCanal()` y luego `enviarNotificacion()` con un mensaje de prueba para cada uno.

**Pista:** Aquí combinamos herencia (para la base común de gestión) con interfaces (para el comportamiento de envío específico).

---

¡Estos ejercicios os darán una base sólida en el uso de herencia e interfaces! Recordad que la clave no es solo que el código funcione, sino que comprendáis por qué elegisteis una clase abstracta o una interfaz, y cómo esto organiza vuestro diseño.

¡Mucha suerte!
