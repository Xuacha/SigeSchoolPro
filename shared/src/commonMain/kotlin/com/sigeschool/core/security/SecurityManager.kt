package com.sigeschool.core.security

expect class SecurityManager {
    fun getDatabasePassphrase(): String
    fun hasPassphrase(): Boolean
    fun isBiometricSupported(): Boolean
}
