package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.NominaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NominaDao {
    @Query("SELECT * FROM payroll_nominas WHERE institutionId = :institutionId")
    fun getAllNominas(institutionId: String): Flow<List<NominaEntity>>

    @Query("SELECT * FROM payroll_nominas WHERE employeeId = :employeeId AND institutionId = :institutionId")
    fun getNominasByEmployee(employeeId: String, institutionId: String): Flow<List<NominaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNomina(nomina: NominaEntity)

    @Update
    suspend fun updateNomina(nomina: NominaEntity)

    @Delete
    suspend fun deleteNomina(nomina: NominaEntity)

    @Query("DELETE FROM payroll_nominas WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteNominaById(id: String, institutionId: String)
}
