package com.sigeschool.core.security

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.SecureRandom

actual class SecurityManager(private val context: Context) {
    private val PREF_NAME = "auditpro_security_prefs"
    private val KEY_PASSPHRASE = "db_passphrase"

    actual fun getDatabasePassphrase(): String {
        return try {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            val sharedPrefs = EncryptedSharedPreferences.create(
                context,
                PREF_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

            var passphrase = sharedPrefs.getString(KEY_PASSPHRASE, null)
            if (passphrase == null) {
                passphrase = generateSecurePassphrase()
                sharedPrefs.edit().putString(KEY_PASSPHRASE, passphrase).apply()
            }
            passphrase!!
        } catch (e: Exception) {
            // Fallback en caso de error con el Keystore
            generateSecurePassphrase() 
        }
    }

    private fun generateSecurePassphrase(): String {
        val bytes = ByteArray(32)
        SecureRandom().nextBytes(bytes)
        return bytes.joinToString("") { "%02x".format(it) }
    }

    actual fun hasPassphrase(): Boolean {
        return try {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
            val prefs = EncryptedSharedPreferences.create(
                context,
                PREF_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            prefs.contains(KEY_PASSPHRASE)
        } catch (e: Exception) {
            false
        }
    }

    actual fun isBiometricSupported(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
    }
}
