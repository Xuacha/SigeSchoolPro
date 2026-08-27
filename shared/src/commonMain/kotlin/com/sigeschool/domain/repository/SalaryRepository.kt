package com.sigeschool.domain.repository

import com.sigeschool.domain.model.SalaryRecord
import kotlinx.coroutines.flow.Flow

interface SalaryRepository {
    fun getSalaryRecords(employeeId: String): Flow<List<SalaryRecord>>
    suspend fun saveSalaryRecord(record: SalaryRecord)
    suspend fun syncWithCloud()
}
