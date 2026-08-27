package com.sigeschool.core.security

actual class SecurityManager {
    actual fun getDatabasePassphrase(): String {
        // Stub: In a real implementation, use Keychain
        return "ios_secure_placeholder_passphrase"
    }

    actual fun hasPassphrase(): Boolean = true
    actual fun isBiometricSupported(): Boolean = false // Stub
}
