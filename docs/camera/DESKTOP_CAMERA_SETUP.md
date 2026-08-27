# Configuración de Cámara para Desktop (SigeSchool Pro)

## 1. Librería Seleccionada
Se utiliza **JavaCV** (wrapper de OpenCV) por su robustez y compatibilidad multiplataforma (Windows, macOS, Linux) sin depender de librerías de sistema pre-instaladas gracias a los binarios integrados en los JARs de Maven.

## 2. Dependencias (libs.versions.toml)
```toml
javacpp = "1.5.13"
# ... en libraries
javacv = { module = "org.bytedeco:javacv-platform", version.ref = "javacpp" }
```

## 3. Implementación
El servicio utiliza `OpenCVFrameGrabber` para gestionar el flujo de video.
- **Resolución**: 640x480 (optimizado para enrolamiento facial).
- **Formato**: JPEG.

## 4. Notas de Hardware
- En Linux, requiere permisos en `/dev/video*`.
- En macOS, requiere permisos de cámara en el Info.plist de la app empaquetada.
