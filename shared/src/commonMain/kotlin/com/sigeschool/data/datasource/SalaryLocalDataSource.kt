package com.sigeschool.data.datasource

import com.sigeschool.domain.model.SalaryRecord
import kotlinx.coroutines.flow.Flow

interface SalaryLocalDataSource {
    fun getSalaryRecords(institutionId: String): Flow<List<SalaryRecord>>
    fun getSalaryRecordsByEmployee(employeeId: String): Flow<List<SalaryRecord>>
    suspend fun insertSalaryRecord(record: SalaryRecord)
    suspend fun deleteSalaryRecord(record: SalaryRecord)
    suspend fun getUnsyncedRecords(): List<SalaryRecord>
}
