# Configuración de SQLCipher y Buenas Prácticas

## 1. Introducción
SQLCipher proporciona cifrado transparente de 256 bits AES de archivos de base de datos. En SigeSchool Pro, se utiliza para proteger la persistencia local de Room y SQLDelight.

## 2. Parámetros Técnicos
Para maximizar la seguridad y el rendimiento, se utilizan los siguientes parámetros:

| Parámetro | Valor | Razón |
|-----------|-------|-------|
| `cipher` | `aes-256-gcm` | Proporciona confidencialidad y autenticidad (AEAD). |
| `kdf_iter` | `256000` | Elevado número de iteraciones para mitigar ataques de fuerza bruta. |
| `cipher_page_size` | `4096` | Optimizado para el tamaño de bloque de almacenamiento moderno. |
| `hmac_algorithm` | `hmac-sha512` | Mayor resistencia a colisiones que SHA-1/256. |

## 3. Implementación en Room
La base de datos se inicializa inyectando la llave desde el `KeyRotationManager`:

```kotlin
val factory = SupportOpenHelperFactory(keyRotationManager.getOrCreateCurrentKey().encoded)
val db = Room.databaseBuilder(context, AppDatabase::class.java, "sigeschool_secure.db")
    .openHelperFactory(factory)
    .build()
```

## 4. Mantenimiento y Migración
### Comando `PRAGMA cipher_migrate`
Utilizado para actualizar la estructura de cifrado entre versiones de SQLCipher o para cambiar parámetros de KDF sin recrear la base de datos.
```sql
PRAGMA cipher_migrate;
```

## 5. Mitigación de Ataques Forenses
- **Memoria Volátil:** La llave nunca se almacena en `String` o campos persistentes fuera del TEE.
- **Borrado Seguro:** Al cerrar la sesión, se limpian los buffers de memoria que contienen material sensible.
