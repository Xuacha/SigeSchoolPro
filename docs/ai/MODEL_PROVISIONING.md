# Aprovisionamiento de Modelos ONNX para SigeSchool Pro

## 1. Introducción
SigeSchool Pro utiliza ONNX Runtime para inferencia local de NLP, garantizando la soberanía de datos y funcionamiento offline.

## 2. Generación del Modelo
El modelo actual es una versión cuantizada de un clasificador curricular basado en DistilBERT.

### Pasos para actualizar el modelo:
1. Entrenar/Ajustar el modelo en Python usando `transformers`.
2. Exportar a ONNX:
   ```python
   import torch
   from transformers import AutoModelForSequenceClassification, AutoTokenizer

   model = AutoModelForSequenceClassification.from_pretrained("./my_model")
   dummy_input = torch.zeros(1, 512, dtype=torch.long)
   torch.onnx.export(model, (dummy_input,), "curricular_classifier.onnx")
   ```
3. Cuantizar a INT8 para dispositivos móviles:
   ```python
   from onnxruntime.quantization import quantify_dynamic, QuantType
   quantify_dynamic("curricular_classifier.onnx", "curricular_classifier_int8.onnx", weight_type=QuantType.QUInt8)
   ```

## 3. Ubicación de Archivos
- **Android**: `shared/src/androidMain/assets/models/curricular_classifier.onnx`
- **Desktop**: `desktopApp/src/main/resources/models/curricular_classifier.onnx`

## 4. Fallback Heurístico
Si el archivo no se encuentra o la carga falla, el sistema activará automáticamente `analyzeHeuristic()` basado en Regex, con un log de advertencia (P1).
