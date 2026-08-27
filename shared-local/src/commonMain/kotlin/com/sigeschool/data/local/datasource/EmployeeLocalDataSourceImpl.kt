package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.EmployeeLocalDataSource
import com.sigeschool.data.local.dao.EmployeeDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EmployeeLocalDataSourceImpl(
    private val employeeDao: EmployeeDao
) : EmployeeLocalDataSource {
    override fun getEmployees(institutionId: String): Flow<List<Employee>> {
        return employeeDao.getEmployees(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getActiveEmployees(institutionId: String): Flow<List<Employee>> {
        return employeeDao.getEmployees(institutionId).map { entities ->
            entities.map { it.toDomain() }.filter { it.status == com.sigeschool.domain.model.EmployeeStatus.ACTIVO }
        }
    }

    override suspend fun insertEmployee(employee: Employee) {
        employeeDao.insertEmployee(employee.toEntity())
    }

    override suspend fun insertEmployees(employees: List<Employee>) {
        employeeDao.insertEmployees(employees.map { it.toEntity() })
    }

    override suspend fun getEmployeeById(id: String): Employee? {
        return employeeDao.getEmployeeById(id)?.toDomain()
    }

    override suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee.toEntity())
    }
}
