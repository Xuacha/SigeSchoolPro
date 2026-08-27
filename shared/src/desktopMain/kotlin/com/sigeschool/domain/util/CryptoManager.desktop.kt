package com.sigeschool.domain.util

import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission
import java.security.KeyStore
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

actual object CryptoManager {
    private const val KEY_ALIAS = "SigeSchool_PII_Key_V1"
    private const val KEYSTORE_TYPE = "PKCS12"
    private val KEYSTORE_PATH = System.getProperty("user.home") + "/.sigeschool/keystore.p12"
    private const val KEYSTORE_PASSWORD = "sigeschool_secure_pwd_2026"
    private const val TRANSFORMATION = "AES/GCM/NoPadding"

    private val keyStore: KeyStore by lazy {
        KeyStore.getInstance(KEYSTORE_TYPE).apply {
            val file = File(KEYSTORE_PATH)
            if (file.exists()) {
                file.inputStream().use { load(it, KEYSTORE_PASSWORD.toCharArray()) }
            } else {
                load(null, null)
                val secretKey = generateKey()
                setEntry(
                    KEY_ALIAS,
                    KeyStore.SecretKeyEntry(secretKey),
                    KeyStore.PasswordProtection(KEYSTORE_PASSWORD.toCharArray())
                )
                file.parentFile.mkdirs()
                file.createNewFile()
                setFilePermissions(file)
                file.outputStream().use { store(it, KEYSTORE_PASSWORD.toCharArray()) }
            }
        }
    }

    private fun setFilePermissions(file: File) {
        try {
            val os = System.getProperty("os.name").lowercase()
            if (!os.contains("win")) {
                val perms = setOf(
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE
                )
                Files.setPosixFilePermissions(file.toPath(), perms)
            } else {
                file.setReadable(false, false)
                file.setReadable(true, true)
                file.setWritable(false, false)
                file.setWritable(true, true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun generateKey(): SecretKey {
        return KeyGenerator.getInstance("AES").apply { init(256) }.generateKey()
    }

    private fun getSecretKey(): SecretKey {
        val entry = keyStore.getEntry(KEY_ALIAS, KeyStore.PasswordProtection(KEYSTORE_PASSWORD.toCharArray())) as? KeyStore.SecretKeyEntry
        return entry?.secretKey ?: throw IllegalStateException("Secret key not found")
    }

    actual fun encrypt(data: String): String {
        if (data.isBlank()) return data
        return try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
            val iv = cipher.iv
            val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
            val combined = iv + encrypted
            Base64.getEncoder().encodeToString(combined)
        } catch (e: Exception) {
            "ERR_ENCRYPT:$data"
        }
    }

    actual fun decrypt(data: String): String {
        if (data.isBlank()) return data
        return try {
            val decoded = Base64.getDecoder().decode(data)
            if (decoded.size < 13) return data
            val iv = decoded.copyOfRange(0, 12)
            val encrypted = decoded.copyOfRange(12, decoded.size)
            
            val cipher = Cipher.getInstance(TRANSFORMATION)
            val spec = GCMParameterSpec(128, iv)
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec)
            
            val decrypted = cipher.doFinal(encrypted)
            String(decrypted, Charsets.UTF_8)
        } catch (e: Exception) {
            data
        }
    }

    actual fun encryptSync(data: String): String = encrypt(data)
    actual fun decryptSync(data: String): String = decrypt(data)

    actual suspend fun encryptAsync(data: String): String = encrypt(data)
    actual suspend fun decryptAsync(data: String): String = decrypt(data)

    actual fun getMasterKeyBytes(): ByteArray {
        return getSecretKey().encoded
    }

    actual fun importMasterKey(keyBytes: ByteArray) {
        val secretKey = SecretKeySpec(keyBytes, "AES")
        keyStore.setEntry(
            KEY_ALIAS,
            KeyStore.SecretKeyEntry(secretKey),
            KeyStore.PasswordProtection(KEYSTORE_PASSWORD.toCharArray())
        )
        val file = File(KEYSTORE_PATH)
        file.outputStream().use { keyStore.store(it, KEYSTORE_PASSWORD.toCharArray()) }
    }

    actual fun encryptWithPin(data: ByteArray, pin: String): ByteArray {
        val salt = "SigeSchool_Salt_2026".toByteArray()
        val key = deriveKeyFromPin(pin, salt)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(data)
        return iv + encrypted
    }

    actual fun decryptWithPin(data: ByteArray, pin: String): ByteArray {
        val salt = "SigeSchool_Salt_2026".toByteArray()
        val key = deriveKeyFromPin(pin, salt)
        val iv = data.sliceArray(0 until 12)
        val encrypted = data.sliceArray(12 until data.size)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, key, spec)
        return cipher.doFinal(encrypted)
    }

    private fun deriveKeyFromPin(pin: String, salt: ByteArray): SecretKey {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(pin.toCharArray(), salt, 100000, 256)
        val tmp = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, "AES")
    }
}
