package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentLocalDataSource {
    fun getAllStudents(institutionId: String): Flow<List<Student>>
    fun searchStudents(query: String, institutionId: String): Flow<List<Student>>
    suspend fun insertStudent(student: Student)
    suspend fun updateStudent(student: Student)
    suspend fun deleteStudentById(id: String, institutionId: String)
    suspend fun softDeleteStudentById(id: String, institutionId: String)
    suspend fun getStudentById(id: String, institutionId: String): Student?
    suspend fun getStudentByDni(dni: String, institutionId: String): Student?
    suspend fun getUnsyncedStudents(): List<Student>
    suspend fun deleteAll(institutionId: String)
}
