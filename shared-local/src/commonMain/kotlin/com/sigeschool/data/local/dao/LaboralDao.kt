package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.AdvanceRequestEntity
import com.sigeschool.data.local.entity.PayrollCalculationEntity
import com.sigeschool.data.local.entity.VacationRequestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaboralDao {

    // Vacation Requests
    @Query("SELECT * FROM vacation_requests WHERE employeeId = :employeeId")
    fun getVacationRequests(employeeId: String): Flow<List<VacationRequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacationRequest(request: VacationRequestEntity)

    @Query("UPDATE vacation_requests SET sincronizado = 1 WHERE id = :id")
    suspend fun markVacationSynced(id: String)

    @Query("SELECT * FROM vacation_requests WHERE sincronizado = 0")
    suspend fun getUnsyncedVacations(): List<VacationRequestEntity>

    // Advance Requests
    @Query("SELECT * FROM advance_requests WHERE employeeId = :employeeId")
    fun getAdvanceRequests(employeeId: String): Flow<List<AdvanceRequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdvanceRequest(request: AdvanceRequestEntity)

    @Query("UPDATE advance_requests SET sincronizado = 1 WHERE id = :id")
    suspend fun markAdvanceSynced(id: String)

    @Query("SELECT * FROM advance_requests WHERE sincronizado = 0")
    suspend fun getUnsyncedAdvances(): List<AdvanceRequestEntity>

    // Payroll Calculations
    @Query("SELECT * FROM payroll_calculations WHERE employeeId = :employeeId")
    fun getPayrollCalculations(employeeId: String): Flow<List<PayrollCalculationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayrollCalculation(calculation: PayrollCalculationEntity)

    @Query("UPDATE payroll_calculations SET sincronizado = 1 WHERE id = :id")
    suspend fun markPayrollSynced(id: String)

    @Query("SELECT * FROM payroll_calculations WHERE sincronizado = 0")
    suspend fun getUnsyncedPayroll(): List<PayrollCalculationEntity>

    @Query("SELECT * FROM payroll_calculations WHERE employeeId = :employeeId ORDER BY date DESC")
    suspend fun getPayrollHistory(employeeId: String): List<PayrollCalculationEntity>
    
    @Query("SELECT * FROM payroll_calculations WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getPayrollHistoryByDateRange(startDate: Long, endDate: Long): List<PayrollCalculationEntity>
}
