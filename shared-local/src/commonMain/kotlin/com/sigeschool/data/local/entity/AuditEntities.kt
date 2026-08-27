package com.sigeschool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audit_ledger")
data class AuditEntryEntity(
    @PrimaryKey
    val ledgerIndex: Long,
    val previousHash: String,
    val timestamp: Long,
    val data: String,
    val nonce: String,
    val hash: String
)
