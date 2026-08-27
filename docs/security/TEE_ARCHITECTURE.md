# Arquitectura del Trusted Execution Environment (TEE)

## 1. Visión General
SigeSchool Pro delega la gestión de secretos críticos al hardware del dispositivo mediante el uso de **Android Keystore** en dispositivos móviles y sistemas equivalentes en Desktop.

## 2. Componentes de Hardware
### Android StrongBox / TEE
- **Enclave Seguro:** Las llaves maestras de cifrado se generan dentro del chip de seguridad (Titan M, Samsung Knox, etc.).
- **Aislamiento:** El sistema operativo Android nunca ve la clave privada/secreta. Solo puede solicitar operaciones (firmar/descifrar).
- **Protección contra Rollback:** El hardware impide el uso de llaves si el contador de seguridad del sistema ha sido degradado.

## 3. Implementación Técnica
La configuración de llaves utiliza parámetros restrictivos:
```kotlin
val spec = KeyGenParameterSpec.Builder(alias, PURPOSE_ENCRYPT or PURPOSE_DECRYPT)
    .setBlockModes(BLOCK_MODE_GCM)
    .setEncryptionPaddings(ENCRYPTION_PADDING_NONE)
    .setKeySize(256)
    .setIsStrongBoxBacked(true) // Requerido para hardware dedicado
    .setUserAuthenticationRequired(true) // Biometría vinculada
    .build()
```

## 4. Flujo de Desbloqueo de Datos
1. **App Start:** Solicita la llave de SQLCipher al `KeyRotationManager`.
2. **TEE Request:** El manager solicita al Android Keystore la llave bajo el alias `vN`.
3. **Biometrics:** Se requiere la huella/rostro del usuario para liberar el acceso a la llave dentro del TEE.
4. **Decryption:** El TEE devuelve la llave (o la usa internamente) para abrir la base de datos SQLCipher.

## 5. Paridad en Desktop
- **Windows:** Integración con **Windows Credential Manager** y uso de TPM si está disponible.
- **macOS:** Integración con **Keychain Services** y Secure Enclave (Apple Silicon).
