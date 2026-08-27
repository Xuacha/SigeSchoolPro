package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash_transactions")
data class CashTransactionEntity(
    @PrimaryKey val id: String,
    val institutionId: String,
    val type: String, // "INCOME" o "EXPENSE"
    val concept: String,
    val category: String, // "MATRICULA", "MENSUALIDAD", "OTROS", "GASTOS_OPERATIVOS"
    val amount: Double,
    val paymentMethod: String,
    val personName: String?,
    val reference: String?,
    val timestamp: Long,
    val observations: String?,
    val registradoPorId: String,
    val isSynced: Boolean = false,
    val version: Long = 0,
    val deviceId: String = "",
    val lastModified: Long = 0,
    val syncStatus: Int = 0,
    val syncAttempts: Int = 0
)
