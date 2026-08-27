package com.sigeschool.domain.security

/**
 * Interfaz para el servicio de respaldo y rotación de claves maestras.
 * Asegura la recuperación de datos ante fallos de persistencia local (KeyStore/IndexedDB).
 */
interface KeyBackupService {
    /**
     * Realiza un backup de la clave maestra actual, cifrada con un PIN del usuario.
     * @param pin Frase de paso o PIN para derivar la clave de cifrado del backup.
     */
    suspend fun backupKey(pin: String): Result<Unit>
    
    /**
     * Restaura la clave maestra desde el backup en la nube.
     * @param pin PIN utilizado para descifrar el backup.
     * @return Los bytes de la clave maestra original.
     */
    suspend fun restoreKey(pin: String): Result<ByteArray>
    
    /**
     * Rota la clave maestra actual por una nueva.
     * Re-encripta los datos PII y actualiza el backup.
     */
    suspend fun rotateKey(oldPin: String, newPin: String): Result<Unit>
    
    /**
     * Verifica la existencia de un backup en Supabase Storage.
     */
    suspend fun hasBackup(): Boolean
}
