# Gestión de Rotación de Claves (Compliance ISO 27001)

## 1. Arquitectura de Seguridad
SigeSchool Pro utiliza el **Android Keystore System** para proteger las claves maestras que encriptan la base de datos SQLCipher.

## 2. Estrategia de Rotación
Para cumplir con ISO 27001, las claves no deben ser estáticas. Se implementó un `KeyRotationManager` que:
- Versiona las claves con el prefijo `SigeSchoolMasterKey_v{N}`.
- Utiliza **AES-256-GCM** para máxima seguridad y autenticidad del contenido.
- Permite la coexistencia de claves de diferentes versiones durante procesos de migración.

## 3. Proceso de Migración (Re-encripción)
Cuando se detecta una nueva versión de clave:
1. Se genera la nueva clave `vN+1` en el TEE (Trusted Execution Environment).
2. Se abre la base de datos con la clave `vN`.
3. Se ejecuta el comando `PRAGMA rekey` de SQLCipher para migrar a la clave `vN+1`.
4. Se actualiza el puntero de versión en las SharedPreferences seguras.

## 4. Auditoría
Esta implementación cierra el requisito de rotación periódica de secretos y endurece la postura de seguridad contra ataques de extracción de llaves.
