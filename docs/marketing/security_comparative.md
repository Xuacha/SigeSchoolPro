# SigeSchool Pro vs. Competencia: Comparativa de Seguridad

| Característica | SigeSchool Pro | Aliice / Phidias | Siigo Colegios |
| :--- | :---: | :---: | :---: |
| **Arquitectura de Datos** | **Offline-First (Soberanía Local)** | Cloud-Only (SaaS) | Cloud-Only (SaaS) |
| **Cifrado en Dispositivo** | **SQLCipher AES-256-GCM** | Ninguno / Cache simple | Ninguno / Propietario |
| **Protección por Hardware** | **TEE / StrongBox (Grado Bancario)** | No (Depende del Browser) | No |
| **Rotación de Llaves** | **Automática (KeyRotationManager)** | Manual / Estática | No especificada |
| **Auditoría Inmutable** | **Ledger Local con SHA-256** | Base de Datos Estándar | Logs estándar |
| **Facturación Offline** | **Modo Standby Seguro** | No (Requiere Internet) | No (Requiere Internet) |
| **Cumplimiento ISO 27001** | **Diseñado para Certificación** | Parcial | Parcial |

### Diferenciadores Clave:
1. **Soberanía de Datos:** SigeSchool Pro es el único sistema donde la llave de los datos pertenece físicamente a la institución (en el chip del dispositivo), no al proveedor de software.
2. **Resiliencia Extrema:** La capacidad de facturar de forma legal y segura sin conexión a internet es una ventaja competitiva crítica en regiones con conectividad inestable.
3. **Privacidad de Menores:** Al no centralizar todos los datos en una sola base de datos en la nube (que es un "honeypot" para hackers), SigeSchool Pro reduce drásticamente el radio de impacto de una brecha de seguridad.
