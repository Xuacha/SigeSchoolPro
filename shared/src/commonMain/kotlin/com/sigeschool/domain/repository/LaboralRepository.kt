package com.sigeschool.domain.repository

import com.sigeschool.domain.model.VacationRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.LiquidationCalculation
import com.sigeschool.domain.model.AdvanceRequest
import kotlinx.coroutines.flow.Flow
import com.sigeschool.domain.util.Resource

interface LaboralRepository {
    fun getVacationRequests(employeeId: String): Flow<Resource<List<VacationRequest>>>
    suspend fun submitVacationRequest(request: VacationRequest): Resource<Boolean>
    suspend fun savePayrollCalculation(employeeId: String, calculation: PayrollCalculation): Resource<Boolean>
    suspend fun getLiquidationEstimate(employeeId: String): Resource<LiquidationCalculation?>
    suspend fun calcularAntiguedad(employeeId: String): Int
    suspend fun calcularSalarioBase(employeeId: String): Double
    fun getAdvanceRequests(employeeId: String): Flow<Resource<List<AdvanceRequest>>>
    suspend fun submitAdvanceRequest(request: AdvanceRequest): Resource<Boolean>
    suspend fun updateAdvanceStatus(advanceId: String, status: String): Resource<Boolean>
    suspend fun syncWithCloud()
    
    // Reportes
    suspend fun generateBulkPayrollReport(institutionName: String): Resource<ByteArray>
}
