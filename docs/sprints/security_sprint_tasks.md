# Tareas Detalladas: Sprint de Seguridad v3.1

| ID | Tarea | Descripción Técnica | Estimación | Responsable |
|:---|:---|:---|:---|:---|
| SEC-01 | **Migración Automática SQLCipher** | Implementar `PRAGMA cipher_migrate` en el callback de apertura de Room para manejar cambios de versión de librería y parámetros KDF. | 8h | Staff Engineer |
| SEC-02 | **Key Rotation v2.0** | Refinar `KeyRotationManager` para disparar rotación basada en tiempo (90 días) y persistir la última versión en SharedPreferences encriptadas. | 6h | Backend Dev |
| SEC-03 | **Adapter Keychain (macOS)** | Implementar la lógica de `security` nativa para macOS usando `Security.framework` para almacenar la frase de paso de SQLCipher. | 12h | Desktop Dev |
| SEC-04 | **Adapter Credential Mgr (Win)** | Implementar el uso de `Windows Credential Manager` vía JNA/C++ para persistencia segura de llaves en Windows 10/11. | 12h | Desktop Dev |
| SEC-05 | **AuditLedger Timestamping** | Integrar un servicio de sellado de tiempo (RFC 3161) para garantizar que los hashes del Ledger no fueron generados retroactivamente. | 8h | Staff Engineer |
| SEC-06 | **Pruebas de Extracción Forense** | Intentar extraer la DB de un dispositivo con Root y Bootloader desbloqueado para validar que StrongBox mantiene la llave inaccesible. | 10h | QA / Security |
| SEC-07 | **Documentación de API Críptica** | Documentar los endpoints internos de criptografía para que nuevos desarrolladores no rompan la cadena de confianza. | 4h | Staff Engineer |

**Total de Esfuerzo Estimado:** 60 horas-hombre.
