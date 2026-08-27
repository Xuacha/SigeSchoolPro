package com.sigeschool.domain.repository

import com.sigeschool.domain.model.FeePayment
import kotlinx.coroutines.flow.Flow

interface FeeRepository {
    fun getPayments(studentId: String): Flow<List<FeePayment>>
    suspend fun savePayment(payment: FeePayment)
    suspend fun syncWithCloud()
}
