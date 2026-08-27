package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fee_payments")
data class FeePaymentEntity(
    @PrimaryKey
    val id: String,
    val studentId: String,
    val institutionId: String,
    val monto: Double,
    val concepto: String,
    val fecha: String,
    val usuarioRecibe: String,
    val metodoPago: String,
    val receiptUrl: String?,
    val sincronizado: Boolean = false,
    val version: Long = 0,
    val deviceId: String = "",
    val lastModified: Long = 0,
    val syncStatus: Int = 0,
    val syncAttempts: Int = 0
)
