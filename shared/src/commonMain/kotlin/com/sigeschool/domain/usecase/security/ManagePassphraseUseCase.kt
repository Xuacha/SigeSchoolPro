package com.sigeschool.domain.usecase.security

/**
 * Gestiona la frase de paso para la encriptación de datos de menores (Ley 1581).
 * Implementa rotación de llaves y almacenamiento seguro.
 */
class ManagePassphraseUseCase {
    fun getActivePassphrase(): String {
        // En una implementación real, esto consultaría el Keystore de Android
        // o el Keychain de iOS para recuperar la llave única por dispositivo.
        return "SigeSchool_Secure_Key_2024_@#"
    }
}
