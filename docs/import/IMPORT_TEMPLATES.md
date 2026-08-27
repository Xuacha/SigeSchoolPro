# Plantillas de Importación Masiva

Para realizar importaciones exitosas en SigeSchool Pro, los archivos CSV/Excel deben seguir la estructura definida a continuación.

## 1. Importación de Estudiantes y Acudientes
Este proceso vincula automáticamente al estudiante con su representante legal.

### Columnas Obligatorias:
- `estudiante_documento`: (String) Cédula o Tarjeta de Identidad.
- `estudiante_nombres`: (String)
- `estudiante_apellidos`: (String)
- `estudiante_grado`: (String) Ejemplo: "10-01"
- `acudiente_documento`: (String)
- `acudiente_nombres`: (String)
- `acudiente_email`: (String) Para creación de cuenta y notificaciones.
- `acudiente_telefono`: (String) En formato internacional (ej. +57300...).

## 2. Importación de Docentes y Staff
- `documento`: (String)
- `nombres`: (String)
- `apellidos`: (String)
- `cargo`: (String) [DOCENTE, COORDINADOR, ADMINISTRATIVO]
- `email`: (String)
- `asignaturas`: (String) Separadas por comas (ej. "Matemáticas, Física").

## 3. Estados de la Importación
- **PENDIENTE:** Archivo cargado pero no procesado.
- **VALIDANDO:** El sistema verifica formatos y duplicados.
- **PROCESANDO:** Creación de perfiles y envío de credenciales.
- **COMPLETADO:** Proceso finalizado exitosamente.
- **ERROR:** Se detalla la fila y el motivo del fallo.
