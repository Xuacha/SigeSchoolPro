package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getAllStudents(): Flow<List<Student>>
    fun getActiveStudents(): Flow<List<Student>>
    fun getStudentsByStatus(status: StudentStatus): Flow<List<Student>>
    suspend fun getStudentById(id: String): Student?
    suspend fun updateStudentStatus(studentId: String, newStatus: StudentStatus, reason: String? = null): Resource<Unit>
    suspend fun getStudentByDocumentId(documentId: String): Student?
    suspend fun getStudentByEmail(email: String): Student?
    suspend fun saveStudent(student: Student)
    suspend fun deleteStudent(id: String)
    suspend fun softDeleteStudent(id: String, reason: String): Resource<Unit>
    suspend fun searchStudents(query: String): List<Student>
    suspend fun syncStudents(): Resource<Unit>
    fun getStudentsByDocente(userId: String): Flow<List<Student>>
    suspend fun getProgramsByStudent(studentId: String): List<String>
    fun getStudentsByProgram(programId: String): Flow<List<Student>>
    suspend fun clearAllStudents(institutionId: String)
}
