package com.sigeschool.data.repository

import com.sigeschool.domain.model.FeePayment
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface FeeRepository {
    fun getPaymentsByStudent(studentId: Long): Flow<Resource<List<FeePayment>>>
    fun getAllPayments(institutionId: String): Flow<Resource<List<FeePayment>>>
    suspend fun registerPayment(payment: FeePayment): Resource<Boolean>
    suspend fun syncWithCloud()
    // FIX: expone la generación de URL firmada bajo demanda; el
    // comprobante ya no se sirve desde una URL pública fija.
    suspend fun getReceiptUrl(receiptPath: String): String?
}
