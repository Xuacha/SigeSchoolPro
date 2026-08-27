package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FinancialStatement(
    val title: String,
    val institutionName: String,
    val period: String,
    val sections: List<FinancialSection>,
    val totalLabel: String,
    val totalValue: Double
)

@Serializable
data class FinancialSection(
    val title: String,
    val items: List<FinancialItem>,
    val subtotal: Double
)

@Serializable
data class FinancialItem(
    val code: String,
    val name: String,
    val balance: Double
)

@Serializable
data class TrialBalanceReport(
    val institutionName: String,
    val date: String,
    val items: List<TrialBalanceItem>,
    val totalDebits: Double,
    val totalCredits: Double
)

@Serializable
data class TrialBalanceItem(
    val code: String,
    val name: String,
    val debits: Double,
    val credits: Double,
    val finalBalance: Double
)
