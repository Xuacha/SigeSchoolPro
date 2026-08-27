package com.sigeschool.data.remote

import com.sigeschool.domain.model.FeePayment
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage

/**
 * SEC-07: Aislamiento multi-tenant para pagos y facturación.
 * SEC-13: Validación de pertenencia institucional.
 */
class FeeRemoteDataSource(private val client: SupabaseClient) {

    suspend fun uploadPayment(payment: FeePayment): Boolean {
        // SEC-13: Validar que el pago tenga institución asignada
        if (payment.institutionId.isBlank()) return false
        
        return try {
            client.from("fee_payments").insert(payment)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun uploadReceipt(paymentId: String, pdfBytes: ByteArray, institutionId: String): String? {
        return try {
            val bucket = client.storage.from("receipts")
            // SEC-10: Organizar archivos por institución para evitar acceso cruzado en buckets
            val path = "$institutionId/receipt_$paymentId.pdf"
            bucket.upload(path, pdfBytes) {
                upsert = true
            }
            bucket.publicUrl(path)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getPaymentsByStudent(studentId: String, institutionId: String): List<FeePayment> {
        return client.from("fee_payments").select {
            filter { 
                eq("student_id", studentId)
                // SEC-07: Filtrado obligatorio por institución (evitar IDOR)
                eq("institution_id", institutionId)
            }
        }.decodeList()
    }

    suspend fun getAllPayments(institutionId: String): List<FeePayment> {
        return client.from("fee_payments").select {
            filter { 
                // SEC-07: Filtrado obligatorio
                eq("institution_id", institutionId) 
            }
        }.decodeList()
    }
}
