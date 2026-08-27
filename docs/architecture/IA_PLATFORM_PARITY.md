# Paridad de Plataformas en IA y OCR

## 1. Visión General
Para cumplir con el requerimiento de "IA Local Multiplataforma", SigeSchool Pro ha migrado de soluciones nativas aisladas (ML Kit en Android) a un motor unificado basado en estándares abiertos.

## 2. Tecnologías Utilizadas

| Componente | Android | Desktop (JVM) | Web (WasmJs) |
| :--- | :--- | :--- | :--- |
| **OCR Engine** | ML Kit / TFLite | Tesseract (JavaCPP) | Tesseract.js |
| **Inferencia NLP** | ONNX Runtime | ONNX Runtime | ONNX Runtime (JS) |
| **Formato Modelo** | ONNX | ONNX | ONNX |

## 3. Arquitectura del Motor
Se definió la interfaz `IAEngine` en `commonMain`. Cada plataforma implementa los bindings específicos para invocar el motor correspondiente.

### 3.1 Carga de Modelos
Los modelos ONNX se empaquetan en:
- `assets/models/` en Android.
- `src/desktopMain/resources/models/` en Desktop.
- `src/wasmJsMain/resources/models/` en Web.

## 4. Optimización
Los modelos han sido cuantizados a **INT8** para reducir el tamaño y mejorar el tiempo de inferencia en dispositivos con recursos limitados, logrando una latencia < 2s en la mayoría de los casos.
