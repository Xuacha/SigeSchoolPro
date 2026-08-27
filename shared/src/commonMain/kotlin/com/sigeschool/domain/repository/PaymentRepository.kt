package com.sigeschool.domain.repository

import com.sigeschool.domain.model.billing.Payment
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    suspend fun savePayment(payment: Payment): Resource<Unit>
    fun getPaymentsByStudent(studentId: String, institutionId: String): Flow<List<Payment>>
    fun getAllPayments(institutionId: String): Flow<List<Payment>>
    suspend fun getTotalRevenue(institutionId: String): Double
    suspend fun syncPayments(institutionId: String): Resource<Unit>
    suspend fun processGatewayPayment(payment: Payment): Resource<String>
}
