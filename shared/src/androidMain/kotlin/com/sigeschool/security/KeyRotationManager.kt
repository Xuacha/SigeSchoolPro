package com.sigeschool.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import timber.log.Timber
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 * Gestiona la rotación de claves maestras en el Android Keystore (ISO 27001).
 * Implementa versionamiento de claves para permitir migraciones de datos encriptados.
 */
class KeyRotationManager {
    private val KEY_PROVIDER = "AndroidKeyStore"
    private val KEY_ALIAS_PREFIX = "SigeSchoolMasterKey_v"
    private val CURRENT_KEY_VERSION = 1 // Incrementar para disparar rotación

    private val keyStore = KeyStore.getInstance(KEY_PROVIDER).apply { load(null) }

    fun getCurrentKeyAlias(): String = "$KEY_ALIAS_PREFIX$CURRENT_KEY_VERSION"

    /**
     * Obtiene la clave actual o la crea si no existe.
     */
    fun getOrCreateCurrentKey(): SecretKey {
        val alias = getCurrentKeyAlias()
        if (!keyStore.containsAlias(alias)) {
            generateNewKey(alias)
        }
        return (keyStore.getEntry(alias, null) as KeyStore.SecretKeyEntry).secretKey
    }

    /**
     * Inicia el proceso de rotación (Simulado para esta fase).
     * En producción, esto re-encriptaría la base de datos SQLCipher.
     */
    fun rotateKey(): Boolean {
        return try {
            val newVersion = CURRENT_KEY_VERSION + 1
            val newAlias = "$KEY_ALIAS_PREFIX$newVersion"
            generateNewKey(newAlias)
            
            Timber.i("Nueva clave maestra v$newVersion generada en Keystore.")
            
            val success = com.sigeschool.util.rekeyDatabase(newAlias)
            success
        } catch (e: Exception) {
            Timber.e(e, "Fallo en la rotación de claves")
            false
        }
    }

    private fun generateNewKey(alias: String) {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_PROVIDER)
        val spec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()
        
        keyGenerator.init(spec)
        keyGenerator.generateKey()
        Timber.d("Clave generada exitosamente: $alias")
    }

    /**
     * Verifica si se requiere rotación comparando la versión actual con la persistida.
     */
    fun isRotationRequired(savedVersion: Int): Boolean {
        return CURRENT_KEY_VERSION > savedVersion
    }
}
