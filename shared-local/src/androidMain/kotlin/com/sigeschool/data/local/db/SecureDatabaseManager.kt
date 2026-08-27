package com.sigeschool.data.local.db

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.local.database.getDatabaseBuilder
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.Mac
import javax.crypto.SecretKey

object SecureDatabaseManager {

    private const val KEY_ALIAS = "SigeSchoolMasterKey"
    private const val PREFS_NAME = "crypto_prefs"
    private const val SALT_KEY = "db_salt"

    /**
     * Inicializa la base de datos encriptada usando Biometría o Credenciales del dispositivo
     */
    fun initializeEncryptedDatabase(
        activity: FragmentActivity,
        onSuccess: (AppDatabase) -> Unit,
        onError: (String) -> Unit
    ) {
        val biometricManager = BiometricManager.from(activity)
        val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG or 
                            BiometricManager.Authenticators.DEVICE_CREDENTIAL

        when (biometricManager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                showBiometricPrompt(activity, authenticators, onSuccess, onError)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                // Fallback: Solo para desarrollo o dispositivos sin biometría segura
                onSuccess(createEncryptedDatabase(activity.applicationContext, "SigeSchool_Dev_Insecure_Key"))
            }
            else -> {
                showBiometricPrompt(activity, authenticators, onSuccess, onError)
            }
        }
    }

    private fun showBiometricPrompt(
        activity: FragmentActivity,
        authenticators: Int,
        onSuccess: (AppDatabase) -> Unit,
        onError: (String) -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(activity)

        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    try {
                        val masterKey = getOrCreateMasterKey(activity.applicationContext)
                        val database = createEncryptedDatabase(activity.applicationContext, masterKey)
                        onSuccess(database)
                    } catch (e: Exception) {
                        onError("Error al derivar clave: ${e.message}")
                    }
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onError("Error de autenticación: $errString")
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Acceso Seguro")
            .setSubtitle("Autentícate para desbloquear los datos financieros")
            .setAllowedAuthenticators(authenticators)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun getOrCreateSalt(context: Context): ByteArray {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        var saltHex = prefs.getString(SALT_KEY, null)
        if (saltHex == null) {
            val saltBytes = ByteArray(16) // 128 bits
            SecureRandom().nextBytes(saltBytes)
            saltHex = saltBytes.joinToString("") { "%02x".format(it) }
            prefs.edit().putString(SALT_KEY, saltHex).apply()
        }
        return hexStringToByteArray(saltHex)
    }

    private fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        for (i in 0 until len step 2) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
        }
        return data
    }

    private fun getOrCreateMasterKey(context: Context): String {
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }

        if (!keyStore.containsAlias(KEY_ALIAS)) {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val spec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setUserAuthenticationRequired(true)
                .setUserAuthenticationValidityDurationSeconds(30) // Clave válida por 30s tras auth
                .build()

            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }

        val secretKey = keyStore.getKey(KEY_ALIAS, null) as SecretKey
        
        // Derivación determinista de la frase de paso usando HMAC-SHA256
        // Hallazgo A.1 - BAJO: Se usa un salt aleatorio persistente en lugar de uno estático
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(secretKey)
        val salt = getOrCreateSalt(context)
        val derivedKeyBytes = mac.doFinal(salt)
        
        return derivedKeyBytes.joinToString("") { "%02x".format(it) }
    }

    private fun createEncryptedDatabase(context: Context, passphrase: String): AppDatabase {
        return getDatabaseBuilder(context, passphrase)
            .setJournalMode(androidx.room.RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .build()
    }
}
