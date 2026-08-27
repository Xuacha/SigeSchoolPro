package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.FeePaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeePaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payment: FeePaymentEntity)

    @Query("SELECT * FROM fee_payments WHERE studentId = :studentId ORDER BY fecha DESC")
    fun getPaymentsByStudent(studentId: String): Flow<List<FeePaymentEntity>>

    @Query("SELECT * FROM fee_payments WHERE institutionId = :institutionId ORDER BY fecha DESC")
    fun getAllPayments(institutionId: String): Flow<List<FeePaymentEntity>>

    @Query("SELECT * FROM fee_payments WHERE sincronizado = 0")
    suspend fun getUnsyncedPayments(): List<FeePaymentEntity>

    @Query("UPDATE fee_payments SET sincronizado = 1, receiptUrl = :url WHERE id = :id")
    suspend fun markAsSynced(id: String, url: String?)

    @Query("SELECT * FROM fee_payments WHERE id = :id")
    suspend fun getPaymentById(id: String): FeePaymentEntity?
}
