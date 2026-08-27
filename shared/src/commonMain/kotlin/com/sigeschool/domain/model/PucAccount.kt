package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class AccountType {
    ASSET,          // Clase 1
    LIABILITY,      // Clase 2
    EQUITY,         // Clase 3
    INCOME,         // Clase 4
    EXPENSE,        // Clase 5
    COST_OF_SALES,  // Clase 6
    PRODUCTION_COST,// Clase 7
    ORDER_DEBTOR,   // Clase 8
    ORDER_CREDITOR  // Clase 9
}

@Serializable
data class PucAccount(
    val id: String = "",
    val code: String = "",           // Ej: "110505"
    val name: String = "",           // Ej: "CAJA GENERAL"
    val level: Int = 1,              // 1=Clase, 2=Grupo, 3=Cuenta, 4=Subcuenta
    val parentCode: String? = null,
    val accountType: AccountType = AccountType.ASSET,
    val institutionId: String = "",
    val isCustom: Boolean = false,   // Permite cuentas adicionales
    val isActive: Boolean = true
)
