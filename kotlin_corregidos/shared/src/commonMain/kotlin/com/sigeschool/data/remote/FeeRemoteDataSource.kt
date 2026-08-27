package com.sigeschool.data.remote

import com.sigeschool.domain.model.FeePayment
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.CancellationException
import kotlin.time.Duration.Companion.days

class FeeRemoteDataSource(private val client: SupabaseClient) {

    // FIX: el nombre de tabla ahora coincide exactamente con el
    // esquema SQL corregido (fee_payments -> alias "feePayments" para
    // no romper la convención de columnas camelCase de este modelo).
    private val table = "feePayments"

    suspend fun uploadPayment(payment: FeePayment): Boolean {
        return try {
            client.from(table).insert(payment)
            true
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // FIX CRÍTICO (legal + seguridad): antes se usaba `publicUrl()`,
    // que genera una URL fija y adivinable (`receipt_<id>.pdf`) sin
    // ningún control de acceso: cualquier persona con el enlace —o
    // que lo intuya, porque el ID de pago suele ser secuencial/
    // predecible— puede descargar el comprobante, que expone nombre
    // del estudiante/acudiente, monto pagado y datos de la
    // institución. Un comprobante de pago es información financiera
    // de un menor de edad: exponerla sin control es tanto un fallo de
    // seguridad como una infracción a la Ley 1581 de 2012 (ausencia de
    // medidas de seguridad razonables, Art. 4 lit. g). Se reemplaza
    // por una URL firmada de corta duración, generada bajo demanda.
    suspend fun uploadReceipt(paymentId: String, pdfBytes: ByteArray): String? {
        return try {
            val bucket = client.storage.from("receipts")
            val path = "receipt_$paymentId.pdf"
            bucket.upload(path, pdfBytes) {
                upsert = true
            }
            // Se guarda solo la ruta interna; la URL firmada se genera
            // en el momento de consumo (ver getReceiptUrl).
            path
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Genera una URL firmada, válida por 1 día, para descargar un
     * comprobante ya subido. Debe llamarse justo antes de mostrar/
     * compartir el recibo, nunca almacenarse de forma permanente.
     * Requiere que el bucket "receipts" NO esté marcado como público
     * en el panel de Supabase (Storage → receipts → Public: OFF) y que
     * existan políticas RLS sobre storage.objects (ver supabase-schema.sql).
     */
    suspend fun getReceiptSignedUrl(path: String): String? {
        return try {
            client.storage.from("receipts").createSignedUrl(path, 1.days)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // FIX: filtro por columna correcta + antes no filtraba por
    // institución, permitiendo cruzar pagos de un estudiante que ya
    // no pertenece a la institución del usuario autenticado.
    suspend fun getPaymentsByStudent(studentId: Long, institutionId: String): List<FeePayment> {
        return client.from(table).select {
            filter {
                eq("studentId", studentId)
                eq("institutionId", institutionId)
            }
        }.decodeList()
    }

    suspend fun getAllPayments(institutionId: String): List<FeePayment> {
        return client.from(table).select {
            filter { eq("institutionId", institutionId) }
        }.decodeList()
    }
}
