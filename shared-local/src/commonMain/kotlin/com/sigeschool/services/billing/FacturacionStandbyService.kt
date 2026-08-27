package com.sigeschool.services.billing

import com.sigeschool.data.local.dao.billing.BillingDao
import com.sigeschool.data.local.entity.billing.InvoiceEntity
import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.domain.model.billing.InvoiceStatus
import com.sigeschool.domain.model.billing.DocumentType
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * Implementación de facturación en modo "Standby" (Postergación Estratégica).
 * Genera la estructura de factura localmente pero no realiza el envío a la DIAN.
 */
class FacturacionStandbyService(
    private val billingDao: BillingDao
) : FacturacionService {

    override suspend fun generarFactura(pagoId: String): Result<Invoice> {
        return try {
            val timestamp = Clock.System.now().toEpochMilliseconds()
            val dummyEntity = InvoiceEntity(
                id = "INV-$pagoId",
                pagoId = pagoId,
                number = "SETT-001",
                studentId = "0",
                studentName = "Estudiante Temporal",
                parentName = "Acudiente Temporal",
                parentId = "0",
                grade = "N/A",
                institutionId = "INST-001",
                date = timestamp,
                dueDate = timestamp + 2592000000,
                status = "PENDIENTE_ENVIO",
                type = "FACTURA_VENTA",
                totalAmount = 0.0,
                paidAmount = 0.0,
                balance = 0.0,
                concept = "Cobro Rápido",
                observations = "Factura generada en modo Standby",
                cufe = null,
                qrCode = "DUMMY_QR_DIAN_STANDBY",
                xmlUrl = null,
                digitalSignatureUrl = null,
                isSynced = false
            )
            
            billingDao.insertInvoice(dummyEntity)
            
            // Mapeo a Domain Model para cumplir con la interfaz
            val domainInvoice = Invoice(
                id = dummyEntity.id,
                pagoId = pagoId,
                number = dummyEntity.number,
                studentId = dummyEntity.studentId,
                studentName = dummyEntity.studentName,
                parentName = dummyEntity.parentName,
                parentId = dummyEntity.parentId,
                grade = dummyEntity.grade,
                institutionId = dummyEntity.institutionId,
                date = Instant.fromEpochMilliseconds(dummyEntity.date),
                dueDate = Instant.fromEpochMilliseconds(dummyEntity.dueDate),
                status = InvoiceStatus.DRAFT,
                type = DocumentType.INVOICE_INTERNAL,
                items = emptyList(),
                totalAmount = dummyEntity.totalAmount,
                paidAmount = dummyEntity.paidAmount,
                balance = dummyEntity.balance,
                concept = dummyEntity.concept,
                observations = dummyEntity.observations,
                cufe = dummyEntity.cufe,
                qrCode = dummyEntity.qrCode,
                xmlUrl = dummyEntity.xmlUrl,
                digitalSignatureUrl = dummyEntity.digitalSignatureUrl,
                isSynced = dummyEntity.isSynced
            )
            
            Result.success(domainInvoice)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun enviarFactura(invoiceId: String): Result<Unit> {
        // En modo Standby, el envío se posterga hasta que se active el FacturacionDIANService
        return Result.success(Unit)
    }

    override suspend fun consultarEstado(cufe: String): Result<String> {
        return Result.success("PENDIENTE_EN_STANDBY")
    }

    override suspend fun anularFactura(invoiceId: String, motivo: String): Result<Unit> {
        return try {
            billingDao.deleteInvoice(invoiceId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun iniciarPagoEnLinea(request: com.sigeschool.domain.model.billing.PaymentRequest): Result<com.sigeschool.domain.model.billing.PaymentResponse> {
        return Result.failure(Exception("Pagos en línea no disponibles en modo Standby"))
    }
}
