package com.sigeschool.domain.model.billing

import kotlinx.serialization.Serializable

@Serializable
data class CashTransaction(
    val id: String,
    val institutionId: String,
    val type: CashTransactionType,
    val concept: String,
    val category: String,
    val amount: Double,
    val paymentMethod: String,
    val personName: String?,
    val reference: String?,
    val timestamp: Long,
    val observations: String?,
    val registradoPorId: String,
    val isSynced: Boolean = false
)

enum class CashTransactionType {
    INCOME, EXPENSE
}

data class CashArqueo(
    val initialBalance: Double,
    val totalIncomes: Double,
    val totalExpenses: Double,
    val finalBalance: Double
)
