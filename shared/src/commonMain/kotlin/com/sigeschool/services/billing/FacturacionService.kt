package com.sigeschool.services.billing

import com.sigeschool.domain.model.billing.*

/**
 * Interfaz para el servicio de facturación.
 * Permite alternar entre el modo Standby (local) y el modo DIAN (real).
 */
interface FacturacionService {
    suspend fun generarFactura(pagoId: String): Result<Invoice>
    suspend fun enviarFactura(invoiceId: String): Result<Unit>
    suspend fun consultarEstado(cufe: String): Result<String>
    suspend fun anularFactura(invoiceId: String, motivo: String): Result<Unit>

    // Integración PayU (SEC-03, SEC-04)
    suspend fun iniciarPagoEnLinea(request: PaymentRequest): Result<PaymentResponse>
}
