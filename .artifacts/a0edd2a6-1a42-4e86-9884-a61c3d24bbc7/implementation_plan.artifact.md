# Plan de Migración Multiplataforma: SigeSchoolPro (GestionEscolar)

Este plan detalla la evolución del proyecto **GestionEscolar** a la plataforma multiplataforma **SigeSchoolPro**, abarcando Android, iOS y Web (PWA), manteniendo la lógica de negocio, seguridad y capacidades Offline-First.

## Análisis de Estado Actual
- **Módulos Existentes**: `shared` (core), `shared-local` (Room KMP), `shared-ui` (Compose Multiplatform), `androidApp`, `desktopApp`, `webApp`.
- **Base de Datos**: Room Multiplatform con SQLCipher en Android.
- **UI**: Compose Multiplatform parcialmente implementado.
- **Sincronización**: Repositorios en `shared`, disparadores en `androidApp` (WorkManager).
- **Faltantes**: Soporte para iOS (target y app), integración completa de IA y PDF en todas las plataformas.

## User Review Required

> [!IMPORTANT]
> Se requiere confirmar si se prefiere mantener **Room Multiplatform** para iOS (recomendado dado que ya está en `shared-local`) o si es obligatorio migrar a **SQLDelight** como se mencionó en el prompt inicial. Room KMP ya soporta iOS y facilitaría la migración.

> [!WARNING]
> La implementación de **SQLCipher en iOS** requiere la integración de pods nativos y una configuración específica en el `DatabaseBuilder` de iOS.

## Proposed Changes

### 1. Configuración Global de Multiplataforma

#### [MODIFY] [settings.gradle.kts](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/settings.gradle.kts)
- Incluir correctamente todos los módulos: `:shared`, `:shared-local`, `:shared-ui`, `:androidApp`, `:iosApp`, `:desktopApp`, `:webApp`.

#### [MODIFY] [libs.versions.toml](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/gradle/libs.versions.toml)
- Añadir versiones y bibliotecas necesarias para iOS y Web (Ktor, Room, SQLCipher iOS).

---

### 2. Capa de Datos (Compartida y Específica)

#### [MODIFY] [shared/build.gradle.kts](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/shared/build.gradle.kts)
- Añadir targets de iOS (`iosArm64`, `iosX64`, `iosSimulatorArm64`).

#### [MODIFY] [shared-local/build.gradle.kts](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/shared-local/build.gradle.kts)
- Añadir targets de iOS.
- Configurar el plugin de Room para generar el esquema de iOS.

#### [NEW] [DatabaseBuilder (iOS)](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/shared-local/src/iosMain/kotlin/com/sigeschool/data/local/database/PlatformAppDatabase.kt)
- Implementar el constructor de la base de datos para iOS usando `NSDocumentDirectory`.

---

### 3. Lógica de Negocio y Sincronización

#### [MODIFY] [shared/commonMain/.../SyncManager.kt](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/shared/src/commonMain/kotlin/com/sigeschool/services/SyncManager.kt)
- Crear una interfaz `SyncManager` y su implementación compartida que pueda ser llamada desde WorkManager (Android) y BackgroundTasks (iOS).

---

### 4. Interfaz de Usuario (Compose Multiplatform)

#### [MODIFY] [shared-ui/build.gradle.kts](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/shared-ui/build.gradle.kts)
- Añadir targets de iOS.
- Configurar la exportación de recursos para iOS.

#### [NEW] [iosApp](file:///C:/Users/ecest/OneDrive/SigeSchoolpro/iosApp)
- Crear el proyecto Xcode básico que inicializa el `ComposeUIViewController` desde la capa compartida.

---

### 5. Funcionalidades Especiales (QR, PDF, IA)

#### [MODIFY] [shared-ui/commonMain/util/CameraProvider.kt]
- Definir `expect fun CameraView(...)` y sus implementaciones nativas (CameraX en Android, UIViewControllerRepresentable de AVFoundation en iOS).

#### [MODIFY] [shared/commonMain/util/PdfGenerator.kt]
- Implementar generador de PDF usando una librería compatible con KMP (ej. iText Core).

## Verification Plan

### Automated Tests
- Ejecutar `./gradlew :shared:allTests` para verificar lógica compartida.
- Pruebas unitarias de Room en iOS Simulator.

### Manual Verification
- Compilar y ejecutar en Android Emulator.
- Compilar y ejecutar en iOS Simulator (requiere macOS).
- Ejecutar `webApp` en el navegador.
- Verificar sincronización offline/online en las tres plataformas.
