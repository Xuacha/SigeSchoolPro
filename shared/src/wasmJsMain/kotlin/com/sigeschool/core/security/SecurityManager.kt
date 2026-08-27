package com.sigeschool.core.security

actual class SecurityManager {
    actual fun getDatabasePassphrase(): String = "web_passphrase"
    actual fun hasPassphrase(): Boolean = true
    actual fun isBiometricSupported(): Boolean = false
}
