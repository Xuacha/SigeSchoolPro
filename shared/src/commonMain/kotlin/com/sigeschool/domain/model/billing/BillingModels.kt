package com.sigeschool.domain.model.billing

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import com.sigeschool.domain.util.Syncable

@Serializable
enum class BillingStatus {
    PENDING, PAID, PARTIAL, CANCELLED, OVERDUE, DIAN_PROCESSED
}

@Serializable
enum class PaymentMethod {
    EFECTIVO, TRANSFERENCIA, PSE, TARJETA_DEBITO, TARJETA_CREDITO, NEQUI, DAVIPLATA, PAYPAL, CHEQUE, CRIPTO,
    CASH, CARD, TRANSFER, WOMPI, STRIPE
}

@Serializable
enum class DocumentType {
    INVOICE_ELECTRONIC, // Factura Electrónica de Venta
    INVOICE_INTERNAL,   // Recibo interno
    CREDIT_NOTE,        // Nota Crédito
    DEBIT_NOTE          // Nota Débito
}

@Serializable
enum class InvoiceStatus {
    DRAFT,      // Modo Standby (Local)
    SENT,       // Enviado a la DIAN
    ACCEPTED,   // Aceptado por la DIAN
    REJECTED,   // Rechazado por la DIAN
    ANNULLED    // Anulada
}

@Serializable
data class Invoice(
    override val id: String,
    val pagoId: String,
    val number: String, // Prefijo + Consecutivo (ej. SETT 1)
    val studentId: String,
    val studentName: String,
    val parentName: String,
    val parentId: String, // Documento del responsable económico
    val grade: String,
    val institutionId: String,
    val date: Instant,
    val dueDate: Instant,
    val status: InvoiceStatus,
    val type: DocumentType,
    val items: List<InvoiceItem>,
    val totalAmount: Double,
    val paidAmount: Double,
    val balance: Double,
    val concept: String,
    val observations: String? = null,
    val cufe: String? = null,
    val qrCode: String? = null,
    val xmlUrl: String? = null,
    val digitalSignatureUrl: String? = null,
    override val version: Long = 0L,
    override val deviceId: String = "",
    override val lastModified: Long = 0L,
    val isSynced: Boolean = false
) : Syncable {
    val isOverdue: Boolean get() = status != InvoiceStatus.ACCEPTED && status != InvoiceStatus.ANNULLED
}

@Serializable
data class InvoiceItem(
    val id: String,
    val categoryId: String,
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val discount: Double = 0.0,
    val tax: Double = 0.0,
    val total: Double
)

@Serializable
data class PaymentRecord(
    val id: String,
    val invoiceId: String,
    val amount: Double,
    val date: Instant,
    val paymentMethod: PaymentMethod,
    val reference: String? = null, // Numero de comprobante / hash
    val institutionId: String,
    val registrarId: String,
    val isSynced: Boolean = false
)

@Serializable
data class FeeCategory(
    val id: String,
    val name: String,
    val basePrice: Double,
    val isRecurring: Boolean = true,
    val appliesToGrades: List<String> = emptyList()
)
