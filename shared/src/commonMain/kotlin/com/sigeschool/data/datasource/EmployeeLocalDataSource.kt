package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeLocalDataSource {
    fun getEmployees(institutionId: String): Flow<List<Employee>>
    fun getActiveEmployees(institutionId: String): Flow<List<Employee>>
    suspend fun insertEmployee(employee: Employee)
    suspend fun insertEmployees(employees: List<Employee>)
    suspend fun getEmployeeById(id: String): Employee?
    suspend fun deleteEmployee(employee: Employee)
}
