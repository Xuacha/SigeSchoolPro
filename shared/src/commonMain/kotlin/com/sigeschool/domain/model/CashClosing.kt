package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CashClosing(
    val id: String = "",
    val date: String,
    val institutionId: String,
    val totalCash: Double,
    val totalTransfer: Double,
    val totalOther: Double,
    val totalGeneral: Double,
    val closedBy: String,
    val closingTimestamp: Long,
    val observations: String = "",
    val isSynced: Boolean = false
)

@Serializable
data class CashClosingSummary(
    val date: String,
    val payments: List<FeePayment>,
    val totalByMethod: Map<String, Double>,
    val totalGeneral: Double
)
