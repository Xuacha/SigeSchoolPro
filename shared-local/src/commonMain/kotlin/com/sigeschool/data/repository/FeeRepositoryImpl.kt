package com.sigeschool.data.repository

import com.sigeschool.data.datasource.FeeLocalDataSource
import com.sigeschool.data.remote.FeeRemoteDataSource
import com.sigeschool.domain.model.FeePayment
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import com.sigeschool.util.ReceiptGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeeRepositoryImpl(
    private val localDataSource: FeeLocalDataSource,
    private val remoteDataSource: FeeRemoteDataSource,
    private val receiptGenerator: ReceiptGenerator,
    private val studentRepository: StudentRepository
) : FeeRepository {

    override fun getPaymentsByStudent(studentId: String): Flow<Resource<List<FeePayment>>> {
        // Asumimos que necesitamos la institución para el remote source, 
        // pero la interfaz no la provee. Por ahora usamos un placeholder o extraemos de los datos locales si es posible.
        // Sin embargo, según la interfaz FeeRepository, no tenemos institutionId aquí.
        // Mirando FeeRemoteDataSource, requiere institutionId.
        // Esto sugiere que la interfaz FeeRepository o StudentRepository necesitan actualización.
        return networkBoundResource(
            query = { localDataSource.getPaymentsByStudent(studentId) },
            fetch = { remoteDataSource.getPaymentsByStudent(studentId, "DEFAULT") },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertPayment(it.copy(sincronizado = true)) }
            }
        )
    }

    override fun getAllPayments(institutionId: String): Flow<Resource<List<FeePayment>>> {
        return networkBoundResource(
            query = { localDataSource.getAllPayments(institutionId) },
            fetch = { remoteDataSource.getAllPayments(institutionId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertPayment(it.copy(sincronizado = true)) }
            }
        )
    }

    override suspend fun registerPayment(payment: FeePayment): Resource<Boolean> {
        return try {
            localDataSource.insertPayment(payment.copy(sincronizado = false))
            
            val success = withContext(Dispatchers.Default) {
                try {
                    val student = studentRepository.getStudentById(payment.studentId.toString(), payment.institutionId)
                    if (student != null) {
                        val pdfBytes = receiptGenerator.generateReceiptPdf(payment, student)
                        val receiptUrl = remoteDataSource.uploadReceipt(payment.id, pdfBytes, payment.institutionId)
                        val finalPayment = payment.copy(receiptUrl = receiptUrl, sincronizado = true)
                        remoteDataSource.uploadPayment(finalPayment)
                        localDataSource.insertPayment(finalPayment)
                        true
                    } else false
                } catch (e: Exception) {
                    false
                }
            }
            
            if (success) Resource.Success(true)
            else Resource.Error("Pago guardado localmente. Se sincronizará al detectar conexión.", true)
            
        } catch (e: Exception) {
            Resource.Error("Error al registrar pago: ${e.message}")
        }
    }

    override suspend fun syncWithCloud() {
        withContext(Dispatchers.Default) {
            val unsynced = localDataSource.getUnsyncedPayments()
            unsynced.forEach { payment ->
                try {
                    val student = studentRepository.getStudentById(payment.studentId.toString(), payment.institutionId)
                    if (student != null) {
                        val pdfBytes = receiptGenerator.generateReceiptPdf(payment, student)
                        val receiptUrl = remoteDataSource.uploadReceipt(payment.id, pdfBytes, payment.institutionId)
                        val finalPayment = payment.copy(receiptUrl = receiptUrl, sincronizado = true)
                        val success = remoteDataSource.uploadPayment(finalPayment)
                        if (success) {
                            localDataSource.markAsSynced(payment.id, receiptUrl)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
