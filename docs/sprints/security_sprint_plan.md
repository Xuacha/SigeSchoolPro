# Plan de Acción: Sprint de Seguridad (Hardening v3.1)

## 1. Objetivo del Sprint
Elevar la automatización de los procesos criptográficos y extender la protección de hardware a las plataformas Desktop, eliminando la intervención manual en las rotaciones de llaves.

## 2. Alcance
- **Automatización de Migración:** Implementar `PRAGMA cipher_migrate` en el flujo de inicio.
- **Portabilidad de Secretos:** Integrar Keychain (macOS) y Credential Manager (Windows) en el módulo `shared-local`.
- **Integridad Inmutable:** Reforzar el AuditLedger con sellado de tiempo (Timestamping) externo.
- **Pentesting Interno:** Simulación de ataques de extracción de base de datos en dispositivos rooteados.

## 3. Cronograma Estimado (2 Semanas)
- **Días 1-3:** Desarrollo de la lógica de migración automática de base de datos.
- **Días 4-6:** Implementación de adaptadores nativos para almacenamiento seguro en Desktop.
- **Días 7-8:** Integración de logs de auditoría con el Ledger de transacciones.
- **Días 9-10:** Pruebas de estrés y validación de seguridad por auditor externo.

## 4. Criterios de Éxito (DoD)
- La base de datos se actualiza a nuevos parámetros de derivación de claves (KDF) sin pérdida de datos.
- Las llaves maestras en Desktop se almacenan fuera del sistema de archivos plano.
- El 100% de los accesos a datos de menores queda registrado en el AuditLedger.
