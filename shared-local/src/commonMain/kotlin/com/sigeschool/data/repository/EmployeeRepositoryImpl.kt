package com.sigeschool.data.repository

import com.sigeschool.data.datasource.EmployeeLocalDataSource
import com.sigeschool.data.remote.EmployeeRemoteDataSource
import com.sigeschool.domain.model.Employee
import kotlinx.coroutines.flow.Flow

class EmployeeRepositoryImpl(
    private val localDataSource: EmployeeLocalDataSource,
    private val remoteDataSource: EmployeeRemoteDataSource,
    private val studentLocalDataSource: com.sigeschool.data.datasource.StudentLocalDataSource
) : EmployeeRepository {
    override fun getEmployees(institutionId: String): Flow<List<Employee>> {
        return localDataSource.getEmployees(institutionId)
    }

    override fun getActiveEmployees(institutionId: String): Flow<List<Employee>> {
        return localDataSource.getActiveEmployees(institutionId)
    }

    override suspend fun addEmployee(employee: Employee): Boolean {
        localDataSource.insertEmployee(employee)
        return remoteDataSource.upsertEmployee(employee)
    }

    override suspend fun getEmployeeById(id: String): Employee? {
        return localDataSource.getEmployeeById(id)
    }

    override suspend fun deleteEmployee(employee: Employee): Boolean {
        localDataSource.deleteEmployee(employee)
        return remoteDataSource.deleteEmployee(employee.id)
    }

    override suspend fun syncEmployees(institutionId: String) {
        try {
            val remoteEmployees = remoteDataSource.getEmployees(institutionId)
            localDataSource.insertEmployees(remoteEmployees)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getStudentsByDocente(docenteId: String): Flow<List<com.sigeschool.domain.model.Student>> {
        // En una implementación real, esto filtraría por las clases asignadas al docente
        // Por ahora devolvemos todos los estudiantes de la institución del docente (simplificado)
        return studentLocalDataSource.getStudents("DEFAULT")
    }
}
