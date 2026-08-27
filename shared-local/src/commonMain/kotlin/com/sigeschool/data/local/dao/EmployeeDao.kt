package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.EmployeeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees WHERE institutionId = :institutionId")
    fun getEmployeesByInstitution(institutionId: String): Flow<List<EmployeeEntity>>

    @Query("SELECT * FROM employees WHERE authUserId = :authUserId AND institutionId = :institutionId LIMIT 1")
    suspend fun getEmployeeByAuthId(authUserId: String, institutionId: String): EmployeeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: EmployeeEntity)

    @Query("SELECT * FROM employees WHERE syncStatus != 0 AND institutionId = :institutionId")
    suspend fun getPendingSyncEmployees(institutionId: String): List<EmployeeEntity>

    @Query("UPDATE employees SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)

    @Query("DELETE FROM employees WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteEmployeeById(id: String, institutionId: String)

    @Delete
    suspend fun deleteEmployee(employee: EmployeeEntity)
}
