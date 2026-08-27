package com.sigeschool.domain.model.billing

import kotlinx.serialization.Serializable

@Serializable
enum class AccountType { AHORROS, CORRIENTE }

@Serializable
enum class AccountStatus { ACTIVA, INACTIVA, PENDIENTE_VALIDACION }

@Serializable
data class BankAccount(
    val id: String,
    val institutionId: String,
    val bankName: String,
    val accountType: AccountType,
    val accountNumber: String, // Note: This will be encrypted in transit/storage
    val holderName: String,
    val holderDni: String,
    val notificationEmail: String? = null,
    val status: AccountStatus = AccountStatus.ACTIVA,
    val createdAt: Long? = null,
    val updatedAt: Long? = null
)

object ColombianBanks {
    val entities = listOf(
        "Bancolombia",
        "Davivienda",
        "Banco de Bogotá",
        "Banco de Occidente",
        "Banco Popular",
        "BBVA Colombia",
        "Itaú Colombia",
        "Citibank Colombia",
        "Banco AV Villas",
        "Banco Colpatria",
        "Scotiabank Colpatria",
        "Banco Caja Social",
        "Banco Falabella",
        "Bancamia",
        "Banco W",
        "Cooperativa Coopcentral",
        "Bancoomeva",
        "Banco Serfinanza",
        "Corficolombiana",
        "Nequi (Davivienda)",
        "Daviplata (Davivienda)",
        "Movii",
        "Lulo Bank",
        "Nu Colombia",
        "RappiPay"
    ).sorted()
    
    fun getPayuBankCode(entity: String): String? {
        return when (entity) {
            "Bancolombia" -> "1001"
            "Davivienda" -> "1002"
            "Banco de Bogotá" -> "1003"
            "Banco de Occidente" -> "1004"
            "Banco Popular" -> "1005"
            else -> null
        }
    }
}
