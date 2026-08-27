package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash_closings")
data class CashClosingEntity(
    @PrimaryKey
    val id: String,
    val date: String,
    val institutionId: String,
    val totalCash: Double,
    val totalTransfer: Double,
    val totalOther: Double,
    val totalGeneral: Double,
    val closedBy: String,
    val closingTimestamp: Long,
    val observations: String,
    val isSynced: Boolean = false
)
