package com.sigeschool.data.repository

import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.billing.Payment
import com.sigeschool.domain.model.billing.PaymentMethod
import com.sigeschool.domain.repository.PaymentRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.data.local.dao.PaymentDao
import com.sigeschool.data.local.entity.PaymentEntity
import com.sigeschool.data.remote.BillingRemoteDataSource
import com.sigeschool.domain.model.billing.PaymentTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.firstOrNull

class PaymentRepositoryImpl(
    private val paymentDao: PaymentDao,
    private val remoteDataSource: BillingRemoteDataSource,
    private val auditRepository: AuditRepository
) : PaymentRepository {

    override suspend fun savePayment(payment: Payment): Resource<Unit> {
        return try {
            val entity = PaymentEntity(
                id = payment.id,
                institutionId = payment.institutionId,
                studentId = payment.studentId,
                amount = payment.amount,
                date = payment.date,
                concept = payment.concept,
                paymentMethod = payment.paymentMethod.name,
                syncStatus = 1, // PENDING_UPDATE or similar (mapping Int)
                lastModified = 0 // Will be set by DAO if needed, or here
            )
            paymentDao.insertPayment(entity)
            
            auditRepository.log(
                action = "SAVE_PAYMENT",
                resource = "payments/${payment.id}",
                payload = mapOf("amount" to payment.amount, "studentId" to payment.studentId)
            )

            // Try remote sync
            remoteDataSource.updateTransactionStatus(payment.id, "COMPLETED")
            paymentDao.markAsSynced(payment.id, payment.institutionId)
            
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar pago")
        }
    }

    override fun getPaymentsByStudent(studentId: String, institutionId: String): Flow<List<Payment>> {
        return paymentDao.getPaymentsByStudent(studentId, institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllPayments(institutionId: String): Flow<List<Payment>> {
        return paymentDao.getAllPayments(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getTotalRevenue(institutionId: String): Double {
        return paymentDao.getTotalRevenue(institutionId) ?: 0.0
    }

    override suspend fun processGatewayPayment(payment: Payment): Resource<String> {
        // Adaptación de pasarelas a BillingRemoteDataSource
        return try {
            val transaction = PaymentTransaction(
                id = payment.id,
                institutionId = payment.institutionId,
                studentId = payment.studentId,
                monto = payment.amount,
                referenciaExterna = payment.id,
                metodoPago = payment.paymentMethod.name
            )
            val result = remoteDataSource.createPaymentTransaction(transaction)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error("Error en pasarela: ${e.message}")
        }
    }

    override suspend fun syncPayments(institutionId: String): Resource<Unit> {
        return try {
            val pending = paymentDao.getPendingSyncPayments(institutionId)
            pending.forEach { entity ->
                try {
                    remoteDataSource.updateTransactionStatus(entity.id, "COMPLETED")
                    paymentDao.markAsSynced(entity.id, institutionId)
                } catch (e: Exception) {
                    // Continue
                }
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Sync error: ${e.message}")
        }
    }

    private fun PaymentEntity.toDomain(): Payment {
        return Payment(
            id = id,
            institutionId = institutionId,
            studentId = studentId,
            amount = amount,
            date = date,
            concept = concept,
            paymentMethod = try { PaymentMethod.valueOf(paymentMethod) } catch (e: Exception) { PaymentMethod.CASH }
        )
    }
}
