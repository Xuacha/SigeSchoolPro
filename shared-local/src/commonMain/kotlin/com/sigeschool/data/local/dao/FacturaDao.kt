package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.FacturaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FacturaDao {
    @Query("SELECT * FROM cashier_facturas WHERE institutionId = :institutionId")
    fun getAllFacturas(institutionId: String): Flow<List<FacturaEntity>>

    @Query("SELECT * FROM cashier_facturas WHERE studentId = :studentId AND institutionId = :institutionId")
    fun getFacturasByStudent(studentId: String, institutionId: String): Flow<List<FacturaEntity>>

    @Query("SELECT * FROM cashier_facturas WHERE studentId = :studentId AND institutionId = :institutionId")
    suspend fun getByEstudianteSync(studentId: String, institutionId: String): List<FacturaEntity>

    @Query("SELECT * FROM cashier_facturas WHERE id = :id AND institutionId = :institutionId LIMIT 1")
    suspend fun getFacturaById(id: String, institutionId: String): FacturaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFactura(factura: FacturaEntity)

    @Update
    suspend fun updateFactura(factura: FacturaEntity)

    @Query("SELECT SUM(saldoPendiente) FROM cashier_facturas WHERE studentId = :studentId AND institutionId = :institutionId AND estado != 'PAGADA'")
    fun getDeudaTotalEstudiante(studentId: String, institutionId: String): Flow<Double?>
}
