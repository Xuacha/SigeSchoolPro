package com.sigeschool.data.local.entity.billing

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "invoices")
data class InvoiceEntity(
    @PrimaryKey val id: String,
    val pagoId: String,
    val number: String,
    val studentId: String,
    val studentName: String,
    val parentName: String,
    val parentId: String,
    val grade: String,
    val institutionId: String,
    val date: Long,
    val dueDate: Long,
    val status: String,
    val type: String,
    val totalAmount: Double,
    val paidAmount: Double,
    val balance: Double,
    val concept: String,
    val observations: String?,
    val cufe: String?,
    val qrCode: String?,
    val xmlUrl: String?,
    val digitalSignatureUrl: String?,
    val isSynced: Boolean = false,
    val version: Long = 0,
    val deviceId: String = "",
    val lastModified: Long = 0,
    val syncStatus: Int = 0,
    val syncAttempts: Int = 0
)

@Entity(tableName = "invoice_items")
data class InvoiceItemEntity(
    @PrimaryKey val id: String,
    val invoiceId: String,
    val categoryId: String,
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val discount: Double,
    val tax: Double,
    val total: Double
)

@Entity(tableName = "payment_records")
data class PaymentRecordEntity(
    @PrimaryKey val id: String,
    val invoiceId: String,
    val institutionId: String,
    val amount: Double,
    val date: Long,
    val paymentMethod: String,
    val reference: String?,
    val registrarId: String,
    val isSynced: Boolean = false
)

@Entity(tableName = "fee_categories")
data class FeeCategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val basePrice: Double,
    val isRecurring: Boolean,
    val appliesToGrades: String // Almacenado como CSV o JSON string
)
