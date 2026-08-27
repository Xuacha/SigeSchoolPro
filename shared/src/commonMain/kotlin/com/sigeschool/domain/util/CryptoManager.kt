package com.sigeschool.domain.util

expect object CryptoManager {
    fun encrypt(data: String): String
    fun decrypt(data: String): String
    suspend fun encryptAsync(data: String): String
    suspend fun decryptAsync(data: String): String
    
    fun encryptSync(data: String): String
    fun decryptSync(data: String): String
    
    // Métodos para Backup de Claves
    fun getMasterKeyBytes(): ByteArray
    fun importMasterKey(keyBytes: ByteArray)
    
    // Métodos para cifrado con PIN (PBKDF2 + AES)
    fun encryptWithPin(data: ByteArray, pin: String): ByteArray
    fun decryptWithPin(data: ByteArray, pin: String): ByteArray
}

suspend fun Double.toEncryptedString(): String = CryptoManager.encryptAsync(this.toString())
fun Double.toEncryptedStringSync(): String = CryptoManager.encryptSync(this.toString())
suspend fun String.toDecryptedDouble(): Double = CryptoManager.decryptAsync(this).toDoubleOrNull() ?: 0.0
fun String.toDecryptedDoubleSync(): Double = CryptoManager.decryptSync(this).toDoubleOrNull() ?: 0.0
