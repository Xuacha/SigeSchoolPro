package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.SalaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SalaryDao {
    @Query("SELECT * FROM salaries WHERE institutionId = :institutionId")
    fun getAllSalaries(institutionId: String): Flow<List<SalaryEntity>>

    @Query("SELECT * FROM salaries WHERE employeeId = :employeeId AND institutionId = :institutionId")
    fun getSalariesByEmployee(employeeId: String, institutionId: String): Flow<List<SalaryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSalary(salary: SalaryEntity)

    @Delete
    suspend fun deleteSalary(salary: SalaryEntity)

    @Query("DELETE FROM salaries WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteSalaryById(id: String, institutionId: String)
}
