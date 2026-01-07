# Ejercicios: Configuración de Proyectos Android (Moderno)

**Entorno de Trabajo:**
*   **IDE:** Android Studio (versión reciente recomendada)
*   **Lenguaje de Build:** Kotlin DSL (`.kts`)
*   **Gestión de Dependencias:** Version Catalogs (`libs.versions.toml`)
*   **UI Framework:** Jetpack Compose

---

## Objetivos

En esta práctica aprenderás a desenvolverte en la estructura de un proyecto Android moderno. Dejarás de ver los ficheros de configuración como "magia negra" y entenderás qué función cumple cada uno.
*   Manipular `build.gradle.kts` (Level Project y Level Module).
*   Entender y modificar `libs.versions.toml` para gestionar versiones centralizadas.
*   Configurar el `AndroidManifest.xml` básico.
*   Personalizar propiedades de la compilación en `gradle.properties`.

---

## Ejercicio 1: Creación y Exploración ("La Autopsia")

1.  **Crea un nuevo proyecto** en Android Studio.
    *   Selecciona la plantilla **"Empty Activity"** (La que tiene el logo de Compose).
    *   **Name:** `ConfigPractice`
    *   **Package name:** `es.com.configpractice`
    *   **Build configuration language:** `Kotlin DSL (build.gradle.kts) + version catalog` -> **Importante**.

2.  **Exploración:** Una vez indexado el proyecto, localiza y abre los siguientes ficheros. Responde mentalmente (o en un txt) qué crees que hace cada uno:
    *   `settings.gradle.kts`
    *   `build.gradle.kts` (Project: ConfigPractice)
    *   `app/build.gradle.kts` (Module: app)
    *   `gradle/libs.versions.toml`
    *   `app/src/main/AndroidManifest.xml`

> **Nota del Profesor:** el `libs.versions.toml` es el corazón de tus dependencias. Ya no se "tiran" las versiones sueltas en el `build.gradle` si queremos hacerlo bien.

---

## Ejercicio 2: Gestión de Dependencias (El "Baile" del TOML)

Vamos a añadir una librería externa muy común para logs: **Timber**.

1.  Abre `gradle/libs.versions.toml`.
2.  **Añade la versión** en la sección `[versions]`:
    ```toml
    timber = "5.0.1"
    ```
3.  **Define la librería** en la sección `[libraries]`:
    ```toml
    timber = { group = "com.jakewharton.timber", name = "timber", version.ref = "timber" }
    ```
4.  **Sincroniza** (Sync Project with Gradle Files).
5.  Ahora, ve a `app/build.gradle.kts` y añádela en el bloque `dependencies`:
    ```kotlin
    dependencies {
        // ... otras dependencias
        implementation(libs.timber)
    }
    ```
6.  **Comprobación:** Ve a tu `MainActivity.kt` e intenta escribir `Timber.plant(Timber.DebugTree())` en el `onCreate`. Si te deja importarlo, ¡felicidades! Has añadido una dependencia al estilo moderno.



---

## Ejercicio 3: Configurando el Build (Jugando con los SDKs)

Android evoluciona rápido. A veces necesitamos soportar móviles antiguos, o usar funciones de los nuevos.

1.  Abre `app/build.gradle.kts`.
2.  Localiza el bloque `android { defaultConfig { ... } }`.
3.  **Min SDK:** Cambia el `minSdk` a **24** (Android 7.0). Sincroniza. ¿Por qué haríamos esto? (Respuesta: Para usar características de Java 8+ nativas o reducir soporte a móviles muy obsoletos).
4.  **Application ID:** Cambia el `applicationId` a `es.com.otraapp`.
    *   *Reto:* Ejecuta la app en el emulador. Luego vuelve a cambiar el ID al original y ejecútala de nuevo. Verás que se instalan como **dos aplicaciones distintas**. ¡El `applicationId` es el DNI de tu app!
5.  **Build Config:** Activa la generación de la clase `BuildConfig` (que viene desactivada por defecto en versiones recientes para mejorar el rendimiento).
    ```kotlin
    buildFeatures {
        compose = true
        buildConfig = true // <--- Añade esto
    }
    ```
    *   Sincroniza y verifica que puedes acceder a `BuildConfig.APPLICATION_ID` desde tu código Kotlin.

---

## Ejercicio 4: Propiedades del Proyecto (La Sala de Máquinas)

El fichero `gradle.properties` controla CÓMO trabaja Gradle, no QUÉ contiene tu app.

1.  Abre `gradle.properties`.
2.  Observa `org.gradle.jvmargs`. Esto define cuánta RAM puede comerse el proceso de compilación. Si tu PC va lento, bajar esto puede ayudar (aunque compilará más lento).
    *   Modifícalo a: `-Xmx2048m -Dfile.encoding=UTF-8`
3.  Añade una propiedad personalizada para activar el caché de construcción (si no está):
    ```properties
    org.gradle.caching=true
    ```
    *   Esto hace que si recompilas sin cambios, sea instantáneo.

---

## Ejercicio 5: Manifest y Recursos

1.  Abre `app/src/main/AndroidManifest.xml`.
2.  **Permisos:** Tu app necesita internet. Añade el permiso adecuado ANTES de la etiqueta `<application>`:
    ```xml
    <uses-permission android:name="android.permission.INTERNET" />
    ```
3.  **Icono y Nombre:**
    *   No cambies el texto "a fuego" en el Manifest.
    *   Ve a `res/values/strings.xml` (o `values/themes.xml` si usas themes modernos) y cambia el `app_name` a "Mi Config App".
    *   Observa como el Manifest hace referencia a `@string/app_name`. **Nunca escribas texto directo en el Manifest.**

---

## Ejercicio 6: Reto Final (Compose & Kotlin Versioning)

Una de las configuraciones más delicadas en Android moderno es la relación entre la versión de **Kotlin** y la versión del **Compilador de Compose**.

1.  Abre `libs.versions.toml`.
2.  Busca la versión del plugin de Kotlin (`kotlin` o `org-jetbrains-kotlin-android`).
3.  Busca dónde se define la versión del compilador de Compose.
    *   *Nota:* En versiones recientes de Android Studio y el plugin de Gradle 8.x+, la versión del compilador de Compose a veces viene ligada a la versión de Kotlin, o se define en el bloque `composeOptions` del `build.gradle.kts` del módulo app.
4.  **Tu misión:**
    *   Intenta actualizar la versión de Kotlin (en `[versions]`) a una versión SUPERIOR a la que tienes (busca en Google "latest kotlin version").
    *   Sincroniza.
    *   Si falla el build indicando incompatibilidad con Compose, has encontrado el error más común en desarrollo moderno.
    *   **Solución:** Busca la tabla de compatibilidad "Compose Compiler Version Mapping" y ajusta la versión del compilador para que coincida con tu nuevo Kotlin.

> **Reflexión:** Configurar un proyecto no es solo escribir código, es mantener un ecosistema de herramientas en equilibrio.

---

## Ejercicio 7: El Explorador (Investigación)

En el mundo real, no siempre te darán la línea exacta para copiar y pegar. Tienes que buscarla.

1.  **Objetivo:** Quieres añadir una librería para cargar imágenes desde Internet. La más famosa en Compose es **Coil**.
2.  **Investigación:**
    *   Ve a Google y busca "Coil Compose".
    *   Entra en su repositorio de GitHub o web oficial.
    *   Busca la última versión estable (2.x o 3.x).
3.  **Implementación:**
    *   Añade la versión en `[versions]` de tu TOML.
    *   Añade la librería en `[libraries]`. (Pista: suele ser `io.coil-kt:coil-compose`).
    *   Sincroniza y añádela al `build.gradle.kts`.
4.  **Prueba de fuego:**
    *   En tu `MainActivity`, intenta usar `AsyncImage(model = "https://...", contentDescription = null)`. Si funciona, ¡ya sabes incorporar librerías externas!

---

## Ejercicio 8: Estilizando (Material Theme)

Comprender cómo funciona el sistema de temas en Compose es vital para no "hardcodear" colores.

1.  **Localización:** Busca la carpeta `ui.theme` dentro de tu paquete principal.
2.  **Experimentación de Color:**
    *   Abre `Color.kt`. Cambia el `Purple80` (o el color primario que tengas) a un color radicalmente distinto (ej. Rojo: `0xFFFF0000`).
    *   Ejecuta la app. ¿Ha cambiado algo? Si no cambia, asegúrate de que tu móvil/emulador está en modo Dark o Light según el color que hayas tocado (`Purple80` suele ser para Dark Theme).
3.  **El Wrapper Mágico:**
    *   Abre `MainActivity.kt`.
    *   Verás algo como `ConfigPracticeTheme { ... Surface ... }`.
    *   **Prueba:** Elimina (o comenta) el `ConfigPracticeTheme { }` y deja solo el contenido (`Surface` o `Scaffold`).
    *   Ejecuta la app.
    *   **¿Qué ocurre?** La app pierde su identidad y vuelve a los colores morados por defecto de Material, o incluso peor, pierde atributos básicos de texto si no hay un MaterialTheme proveyendo valores.
    *   **Lección:** Siempre envuelve tu UI raíz en tu Theme personalizado.

---

## Ejercicio 9: Iconos (Material 3)

Muchas veces necesitarás iconos que no vienen en el paquete básico de Material 3 (`androidx.compose.material:material-icons-core`). Para tener todos (la "biblia" de iconos), necesitas añadir la versión Extendida.

1.  **Verificación:**
    *   Busca en el `libs.versions.toml` si tienes definida la librería de `material-icons-extended`.
2.  **Añadiendo la dependencia:**
    *   Si no está, añádela siguiendo el mismo proceso que ya conoces:
        *   **Version:** `composeIcons = "1.7.0"` (o la versión que corresponda al BOM de Compose que uses).
        *   **Library:** `androidx-material-icons-extended = { group = "androidx.compose.material", name = "material-icons-extended", version.ref = "composeIcons" }`
3.  **Sincronización:**
    *   Sincroniza y añade el `implementation` en el `build.gradle.kts` del módulo app.
4.  **Uso:**
    *   Ahora deberías poder acceder a iconos como `Icons.Filled.AccountCircle` o iconos más específicos que antes no aparecían.
