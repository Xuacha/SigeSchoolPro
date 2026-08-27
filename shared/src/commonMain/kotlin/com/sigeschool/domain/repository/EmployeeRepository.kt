package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Employee
import com.sigeschool.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {
    fun getEmployees(institutionId: String): Flow<List<Employee>>
    fun getActiveEmployees(institutionId: String): Flow<List<Employee>>
    suspend fun addEmployee(employee: Employee): Boolean
    suspend fun getEmployeeById(id: String): Employee?
    suspend fun deleteEmployee(employee: Employee): Boolean
    suspend fun syncEmployees(institutionId: String)
    fun getStudentsByDocente(docenteId: String): Flow<List<Student>>
}
