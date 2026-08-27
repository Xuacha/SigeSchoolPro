package com.sigeschool.data.datasource

import com.sigeschool.domain.model.AdvanceRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.VacationRequest
import kotlinx.coroutines.flow.Flow

interface LaboralLocalDataSource {
    fun getVacationRequests(employeeId: String): Flow<List<VacationRequest>>
    suspend fun insertVacationRequest(request: VacationRequest)
    suspend fun markVacationSynced(id: String)
    suspend fun getUnsyncedVacations(): List<VacationRequest>

    fun getAdvanceRequests(employeeId: String): Flow<List<AdvanceRequest>>
    suspend fun insertAdvanceRequest(request: AdvanceRequest)
    suspend fun markAdvanceSynced(id: String)
    suspend fun getUnsyncedAdvances(): List<AdvanceRequest>

    fun getPayrollCalculations(employeeId: String): Flow<List<PayrollCalculation>>
    suspend fun insertPayrollCalculation(calculation: PayrollCalculation, employeeId: String, date: Long)
    suspend fun markPayrollSynced(id: String)
    suspend fun getUnsyncedPayroll(): List<PayrollCalculation>
    suspend fun getPayrollHistoryByDateRange(startDate: Long, endDate: Long): List<PayrollCalculation>
}
