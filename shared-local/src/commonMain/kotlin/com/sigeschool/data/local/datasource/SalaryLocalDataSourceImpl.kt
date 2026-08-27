package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.SalaryLocalDataSource
import com.sigeschool.data.local.dao.SalaryDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.SalaryRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SalaryLocalDataSourceImpl(
    private val salaryDao: SalaryDao
) : SalaryLocalDataSource {
    override fun getSalaryRecords(institutionId: String): Flow<List<SalaryRecord>> {
        return salaryDao.getSalaryRecords(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getSalaryRecordsByEmployee(employeeId: String): Flow<List<SalaryRecord>> {
        return salaryDao.getSalaryRecordsByEmployee(employeeId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertSalaryRecord(record: SalaryRecord) {
        salaryDao.insertSalaryRecord(record.toEntity())
    }

    override suspend fun deleteSalaryRecord(record: SalaryRecord) {
        salaryDao.deleteSalaryRecord(record.toEntity())
    }

    override suspend fun getUnsyncedRecords(): List<SalaryRecord> {
        return salaryDao.getUnsyncedRecords().map { it.toDomain() }
    }
}
