package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.CertificadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CertificadoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(certificado: CertificadoEntity): Long

    @Query("SELECT * FROM cashier_certificados WHERE institutionId = :instId AND studentId = :studentId")
    fun getByEstudiante(instId: String, studentId: String): Flow<List<CertificadoEntity>>

    @Query("SELECT * FROM cashier_certificados WHERE id = :id")
    suspend fun getById(id: String): CertificadoEntity?

    @Query("SELECT * FROM cashier_certificados WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<CertificadoEntity>

    @Query("UPDATE cashier_certificados SET syncStatus = 0, lastModified = :timestamp WHERE id = :id")
    suspend fun markAsSynced(id: String, timestamp: Long)
}
