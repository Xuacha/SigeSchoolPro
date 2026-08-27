package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.FeeLocalDataSource
import com.sigeschool.data.local.dao.FeePaymentDao
import com.sigeschool.data.local.entity.FeePaymentEntity
import com.sigeschool.domain.model.FeePayment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeeLocalDataSourceImpl(
    private val feePaymentDao: FeePaymentDao
) : FeeLocalDataSource {
    override fun getPaymentsByStudent(studentId: String): Flow<List<FeePayment>> {
        return feePaymentDao.getPaymentsByStudent(studentId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllPayments(institutionId: String): Flow<List<FeePayment>> {
        return feePaymentDao.getAllPayments(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertPayment(payment: FeePayment) {
        feePaymentDao.insert(payment.toEntity())
    }

    override suspend fun getUnsyncedPayments(): List<FeePayment> {
        return feePaymentDao.getUnsyncedPayments().map { it.toDomain() }
    }

    override suspend fun markAsSynced(id: String, url: String?) {
        feePaymentDao.markAsSynced(id, url)
    }

    private fun FeePaymentEntity.toDomain() = FeePayment(
        id = id,
        studentId = studentId,
        institutionId = institutionId,
        monto = monto,
        concepto = concepto,
        fecha = fecha,
        usuarioRecibe = usuarioRecibe,
        metodoPago = metodoPago,
        receiptUrl = receiptUrl,
        sincronizado = sincronizado
    )

    private fun FeePayment.toEntity() = FeePaymentEntity(
        id = id,
        studentId = studentId,
        institutionId = institutionId,
        monto = monto,
        concepto = concepto,
        fecha = fecha,
        usuarioRecibe = usuarioRecibe,
        metodoPago = metodoPago,
        receiptUrl = receiptUrl,
        sincronizado = sincronizado
    )
}
