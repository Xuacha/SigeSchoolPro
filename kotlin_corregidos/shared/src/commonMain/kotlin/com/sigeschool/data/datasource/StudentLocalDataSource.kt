package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface StudentLocalDataSource {
    fun getAllStudents(institutionId: String): Flow<List<Student>>
    fun searchStudents(institutionId: String, query: String): Flow<List<Student>>
    suspend fun insertStudent(student: Student)
    suspend fun updateStudent(student: Student)
    suspend fun deleteStudentById(id: Long)
    suspend fun softDeleteStudentById(id: Long)
    suspend fun getStudentById(id: Long): Student?
    suspend fun getStudentByDni(dni: String): Student?
    suspend fun getUnsyncedStudents(): List<Student>
}
