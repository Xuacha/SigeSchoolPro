package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: PaymentEntity)

    @Query("SELECT * FROM payments WHERE studentId = :studentId AND institutionId = :institutionId ORDER BY date DESC")
    fun getPaymentsByStudent(studentId: String, institutionId: String): Flow<List<PaymentEntity>>

    @Query("SELECT * FROM payments WHERE institutionId = :institutionId ORDER BY date DESC")
    fun getAllPayments(institutionId: String): Flow<List<PaymentEntity>>

    @Query("SELECT * FROM payments WHERE institutionId = :institutionId")
    suspend fun getAllPaymentsSync(institutionId: String): List<PaymentEntity>

    @Query("SELECT SUM(amount) FROM payments WHERE institutionId = :institutionId")
    suspend fun getTotalRevenue(institutionId: String): Double?

    @Query("SELECT * FROM payments WHERE syncStatus != 0 AND institutionId = :institutionId")
    suspend fun getPendingSyncPayments(institutionId: String): List<PaymentEntity>

    @Query("UPDATE payments SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)

    @Query("DELETE FROM payments WHERE id = :id AND institutionId = :institutionId")
    suspend fun deletePayment(id: String, institutionId: String)

    @Query("UPDATE payments SET studentId = :targetStudentId, syncStatus = 2 WHERE studentId = :sourceStudentId AND institutionId = :institutionId")
    suspend fun migrateStudentPayments(sourceStudentId: String, targetStudentId: String, institutionId: String)
}
