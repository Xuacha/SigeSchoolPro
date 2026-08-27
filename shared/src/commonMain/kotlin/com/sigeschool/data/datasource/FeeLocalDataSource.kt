package com.sigeschool.data.datasource

import com.sigeschool.domain.model.FeePayment
import kotlinx.coroutines.flow.Flow

interface FeeLocalDataSource {
    fun getPaymentsByStudent(studentId: String): Flow<List<FeePayment>>
    fun getAllPayments(institutionId: String): Flow<List<FeePayment>>
    suspend fun insertPayment(payment: FeePayment)
    suspend fun getUnsyncedPayments(): List<FeePayment>
    suspend fun markAsSynced(id: String, url: String?)
}
