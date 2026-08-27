package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.LaboralLocalDataSource
import com.sigeschool.data.local.dao.LaboralDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.AdvanceRequest
import com.sigeschool.domain.model.PayrollCalculation
import com.sigeschool.domain.model.VacationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LaboralLocalDataSourceImpl(
    private val laboralDao: LaboralDao
) : LaboralLocalDataSource {

    override fun getVacationRequests(employeeId: String): Flow<List<VacationRequest>> {
        return laboralDao.getVacationRequests(employeeId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertVacationRequest(request: VacationRequest) {
        laboralDao.insertVacationRequest(request.toEntity())
    }

    override suspend fun markVacationSynced(id: String) {
        laboralDao.markVacationSynced(id)
    }

    override suspend fun getUnsyncedVacations(): List<VacationRequest> {
        return laboralDao.getUnsyncedVacations().map { it.toDomain() }
    }

    override fun getAdvanceRequests(employeeId: String): Flow<List<AdvanceRequest>> {
        return laboralDao.getAdvanceRequests(employeeId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertAdvanceRequest(request: AdvanceRequest) {
        laboralDao.insertAdvanceRequest(request.toEntity())
    }

    override suspend fun markAdvanceSynced(id: String) {
        laboralDao.markAdvanceSynced(id)
    }

    override suspend fun getUnsyncedAdvances(): List<AdvanceRequest> {
        return laboralDao.getUnsyncedAdvances().map { it.toDomain() }
    }

    override fun getPayrollCalculations(employeeId: String): Flow<List<PayrollCalculation>> {
        return laboralDao.getPayrollCalculations(employeeId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertPayrollCalculation(calculation: PayrollCalculation, employeeId: String, date: Long) {
        laboralDao.insertPayrollCalculation(calculation.toEntity(employeeId, date))
    }

    override suspend fun markPayrollSynced(id: String) {
        laboralDao.markPayrollSynced(id)
    }

    override suspend fun getUnsyncedPayroll(): List<PayrollCalculation> {
        return laboralDao.getUnsyncedPayroll().map { it.toDomain() }
    }

    override suspend fun getPayrollHistoryByDateRange(startDate: Long, endDate: Long): List<PayrollCalculation> {
        return laboralDao.getPayrollHistoryByDateRange(startDate, endDate).map { it.toDomain() }
    }
}
