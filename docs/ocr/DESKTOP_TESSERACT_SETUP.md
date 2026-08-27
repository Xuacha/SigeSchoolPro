# Configuración de Tesseract para SigeSchool Pro (Desktop)

Este documento detalla los pasos necesarios para configurar el motor de OCR Tesseract en el entorno Desktop (JVM) utilizando los bindings de Bytedeco (JavaCPP).

## 1. Requisitos de Dependencias

El proyecto utiliza la versión **1.5.10** de Bytedeco. Es fundamental que todas las dependencias estén sincronizadas para evitar errores de enlace nativo.

En `libs.versions.toml`:
- `javacpp = "1.5.10"`
- `leptonica-platform = "1.84.0-1.5.10"`
- `tesseract-platform = "5.4.0-1.5.10"`

## 2. Configuración de Gradle

En el módulo `shared` (o el que contenga la implementación de Desktop), asegúrate de incluir:

```kotlin
val desktopMain by getting {
    dependencies {
        api(libs.javacpp)
        api(libs.tesseract)
        api(libs.tesseract.platform)
        api(libs.leptonica)
        api(libs.leptonica.platform)
    }
}
```

## 3. Manejo de Datos de Entrenamiento (Tessdata)

Tesseract requiere archivos `.traineddata` para funcionar. Por defecto, la aplicación busca la carpeta `tessdata/` en la raíz del proyecto.

1. Descarga `spa.traineddata` (Español) desde el repositorio oficial: [tessdata_best](https://github.com/tesseract-ocr/tessdata_best).
2. Colócalo en: `SigeSchoolpro/tessdata/spa.traineddata`.

## 4. Resolución de Problemas Comunes

### Error: "Unresolved reference 'lept'"
Este error ocurre si el compilador de Kotlin no puede resolver los paquetes generados por JavaCPP. 
- **Solución:** Usa la ruta completa `org.bytedeco.leptonica.global.lept` y asegúrate de llamar a `Loader.load(lept::class.java)` antes de usar cualquier función nativa.

### Error: "Library not found"
Ocurre si faltan los binarios nativos para tu arquitectura.
- **Solución:** Asegúrate de que `tesseract-platform` y `leptonica-platform` estén incluidos con el scope `api` o `implementation` para que Gradle descargue los JARs que contienen las DLLs/so/dylib.

## 5. Gestión de Memoria
Dado que estamos usando memoria nativa (C++), es vital liberar los objetos:
- Usar `pixDestroy(pix)` para imágenes de Leptonica.
- Usar `outText.deallocate()` para el texto extraído.
- Usar `api.Clear()` para limpiar el estado de Tesseract.
