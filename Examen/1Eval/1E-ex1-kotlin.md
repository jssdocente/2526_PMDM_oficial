# Examen 1ª Evaluación: Kotlin

**Módulo:** Programación Multimedia y Dispositivos Móviles (DAM)  
**Contexto:** Lenguaje de programación Kotlin
**Duración:** 3 Horas  
**Herramienta:** IntelliJ IDEA / Android Studio

### ⚠️ Instrucciones Generales

1.  **Descarga y abre** el proyecto `1Eval-ex2.rar`.
2.  Trabajarás principalmente en la carpeta `src/main/kotlin/examen/`.
3.  Cada ejercicio tiene su función `main` ya preparada. Descomenta y haz que funcione.

---

## 📱 Proyecto: App "Juego del Calamar"

Tienes que desarrollar el motor lógico de un videojuego basado en la serie "*El Juego del Calamar*". El sistema gestiona equipos, jugadores y un sorteo mortal mediante bombos de lotería.
El examen consta de 3 ejercicios incrementales. Debes completar el código faltante siguiendo las instrucciones de los comentarios TODO.

El sistema debe simular una partida donde los jugadores reciben un boleto de lotería y son eliminados si su número sale en el bombo principal.

### 📝 Reglas del Juego

1.  **Participantes:** El juego comienza con **9 jugadores** repartidos equitativamente en **3 equipos**.
2.  **Equipos:**
    *   🔴 **ROJO**: Boletos del 1 al 33.
    *   🟢 **VERDE**: Boletos del 34 al 66.
    *   🔵 **AZUL**: Boletos del 67 al 100.
3.  **Asignación:** Cada jugador recibe un número de boleto único (dentro del rango de su equipo) y un nombre aleatorio.
4.  **Mecánica:**
    *   Existe un **Bombo Principal** con bolas del 1 al 100.
    *   En cada turno, se extrae una bola.
    *   Si un jugador tiene ese número de boleto, es **ELIMINADO** inmediatamente.
5.  **Victoria:** El juego continúa hasta que solo quede **un único jugador vivo** (Ganador). Si no hay ningún Jugador vivo al final de la partida, también hay que indicarlo. SIN GANADOR.

> En cada ejercicio del código base, se indican con indicaciones `//TODO` las partes que el alumno debe desarrollar.

---

## 🚀 Instrucciones por Ejercicio

### 1. Los Cimientos (2 puntos)

Define las estructuras básicas de datos.

*   Implementa la interfaz `FuenteAzar` en la clase `Bombo`.
*   El `Bombo` debe usar una `MutableList` para gestionar los números y mezclarlos.
*   Configura el Enumerado `EquipoTipo` y la clase `Jugador`.
*   **Objetivo:** Que el código compile y se puedan crear jugadores y sacar bolas.

### 2. Gestión de Equipos (3 puntos)

Crea la lógica para agrupar jugadores.

*   La clase `Equipo` debe tener su propia lista de jugadores y su propio `Bombo` (limitado a su rango, ej: 1-33).
*   Debes implementar `reclutarJugador()`
*   Debes implementar `getJugadoresVivos()`


### 3. El Motor del Juego (4 puntos)

Unifica todo en la clase `JuegoCalamar`.

*   Usa la `Data Class` proporcionada para devolver los resultados de cada tirada.
*   **Iniciar:** Reparte los jugadores entre los equipos.
*   **Tirada:** Saca una bola del bombo principal (1-100), recorre todos los equipos y jugadores y elimina al que coincida.
*   **Buscar Ganador:** Cuenta manualmente cuántos jugadores quedan vivos en total. Si queda **1**, es el ganador.

Ejemplo salida del Juego:

```text
--- COMIENZA EL JUEGO DEL CALAMAR ---

Estado Inicial de los Equipos:
Equipo ROJO: 3 vivos.
  (1) Hugo [ROJO] -> Boleto: 12
  (2) Lucía [ROJO] -> Boleto: 5
  (3) Martín [ROJO] -> Boleto: 30
Equipo VERDE: 3 vivos.
  (4) Sofía [VERDE] -> Boleto: 45
  (5) Daniel [VERDE] -> Boleto: 60
  (6) Martina [VERDE] -> Boleto: 38
Equipo AZUL: 3 vivos.
  (7) Pablo [AZUL] -> Boleto: 88
  (8) Julia [AZUL] -> Boleto: 70
  (9) Alejandro [AZUL] -> Boleto: 99

--- INICIO DE LAS TIRADAS ---

⚪ Bola 14 extraída. Nadie tenía este boleto.
❌ ¡Bola 88 extraída! Jugador Pablo ELIMINADO.
⚪ Bola 2 extraída. Nadie tenía este boleto.
❌ ¡Bola 5 extraída! Jugador Lucía ELIMINADO.
⚪ Bola 91 extraída. Nadie tenía este boleto.
❌ ¡Bola 45 extraída! Jugador Sofía ELIMINADO.
❌ ¡Bola 30 extraída! Jugador Martín ELIMINADO.

... (Pasan varias tiradas y bolas vacías) ...

❌ ¡Bola 99 extraída! Jugador Alejandro ELIMINADO.
❌ ¡Bola 60 extraída! Jugador Daniel ELIMINADO.
⚪ Bola 1 extraída. Nadie tenía este boleto.
❌ ¡Bola 12 extraída! Jugador Hugo ELIMINADO.

*** SOLO QUEDAN 2 JUGADORES VIVOS ***
(Quedan: Martina [VERDE] y Julia [AZUL])

⚪ Bola 33 extraída. Nadie tenía este boleto.
❌ ¡Bola 70 extraída! Jugador Julia ELIMINADO.

--- FIN DEL JUEGO ---

🏆 ¡TENEMOS UN GANADOR! 🏆
El ganador es: (6) Martina [VERDE] -> Boleto: 38
```

### 4. Bonus (modo maestro) (2 puntos)

**Objetivo:** Implementar **Herencia** y **Polimorfismo** para crear un tipo especial de jugador que tiene una ventaja sobre los demás.

**Contexto:**
La organización del juego ha decidido introducir a unos jugadores especiales llamados "VIPs". Estos jugadores han pagado por un privilegio: **tienen una segunda oportunidad**. Si su número sale en el bombo, no mueren a la primera; gastan su "escudo" y siguen jugando. Solo si sale su número por segunda vez, son eliminados.

### 🛠️ Requerimientos Técnicos

1.  **Modificación de la Clase Base (`Jugador`)**:
    *   Debes preparar la clase `Jugador` existente para que pueda ser heredada.
    *   El método `eliminar()` y `toString()` también deben permitir ser sobrescritos.

2.  **Nueva Clase `JugadorVIP`**:
    *   Debe heredar de `Jugador`.
    *   Debe tener un atributo propio: `escudos` (Entero, inicializado en 1).
    *   **Sobrescribir `eliminar()`**:
        *   Si tiene escudos (> 0): Resta un escudo y muestra un mensaje por consola "¡El Jugador X ha usado su escudo!". **NO cambia el estado a eliminado.**
        *   Si no tiene escudos (= 0): Llama al método `eliminar()` del padre para matarlo definitivamente.
    *   **Sobrescribir `toString()`**:
        *   Debe añadir al texto original el estado del escudo (ej: `[ESCUDO ACTIVO]` o `[ESCUDO ROTO]`).

3.  **Modificación de la Clase `Equipo`**:
    *   Modifica el método `reclutarJugador()`.
    *   **Lógica**: Cada vez que se reclute un jugador cuyo **ID sea múltiplo de 3** (el 3, el 6, el 9...), en lugar de crear un `Jugador` normal, debes instanciar un `JugadorVIP`.


```text
...
⚪ Bola 50 extraída. Nadie tenía este boleto.
❌ ¡Bola 23 extraída! Jugador (3) Martín [VIP] ha usado su escudo! (Sigue vivo).
...
(Turnos después...)
...
❌ ¡Bola 23 extraída! Jugador (3) Martín ELIMINADO.
```


<br><br>


## 💯 Notas

El examen consta de 3 ejercicios incrementales.

| Ejercicio               | Descripción                             | Puntuación                      |
|:------------------------|:----------------------------------------|:--------------------------------|
| **Ejercicio 1**         | Fundamentos (Clases, Interfaces, Enums) | **Imprescindible para aprobar** |
| **Ejercicio 2**         | Gestión de Equipos y Colecciones        | **Imprescindible para aprobar** |
| **Ejercicio 3**         | Motor del Juego e Integración Final     | **4 Puntos**                    |
| **Ejercicio 4 (bonus)** | Funcionalidad avanzada                  | **2 Puntos** (Nota bonus)       |

> **Nota:** Para obtener una calificación de aprobado (5.0), los Ejercicios 1 y 2 deben funcionar correctamente.


## ✅ Criterios de Calificación

*   **Compilación (20%):** El código no debe tener errores de sintaxis (rojos).
*   **Funcionalidad (70%):** La funcionalidad es correcta y se ajusta a las especificaciones indicadas.

¡Mucha suerte! 🚀



