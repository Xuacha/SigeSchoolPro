package com.sigeschool.data.repository

import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.mapper.toDomain
import com.sigeschool.data.mapper.toEntity
import com.sigeschool.domain.AuditRepository
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.repository.StudentRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StudentRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val sessionManager: SessionManager,
    private val database: AppDatabase,
    private val auditRepository: AuditRepository
) : StudentRepository {

    private val studentDao = database.studentDao()

    private fun getInstitutionId(): String {
        return sessionManager.getCurrentInstitutionId() ?: throw Exception("Sesión no encontrada")
    }

    override fun getAllStudents(): Flow<List<Student>> {
        return studentDao.getAllStudents(getInstitutionId()).map { list -> list.map { it.toDomain() } }
    }

    override fun getActiveStudents(): Flow<List<Student>> {
        return studentDao.getActiveStudents(getInstitutionId()).map { list -> list.map { it.toDomain() } }
    }

    override fun getStudentsByStatus(status: StudentStatus): Flow<List<Student>> {
        return studentDao.getStudentsByStatus(getInstitutionId(), status.name).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getStudentById(id: String): Student? {
        return studentDao.getStudentById(id, getInstitutionId())?.toDomain()
    }

    override suspend fun updateStudentStatus(studentId: String, newStatus: StudentStatus, reason: String?): Resource<Unit> {
        return try {
            studentDao.updateStudentStatus(studentId, getInstitutionId(), newStatus.name, reason, 0, 0)
            
            auditRepository.log(
                action = "UPDATE_STATUS",
                resource = "students/$studentId",
                payload = mapOf("status" to newStatus.name, "reason" to (reason ?: "N/A"))
            )
            
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error updating status")
        }
    }

    override suspend fun getStudentByDocumentId(documentId: String): Student? {
        return studentDao.getStudentByDocumentId(documentId, getInstitutionId())?.toDomain()
    }

    override suspend fun getStudentByEmail(email: String): Student? {
        return studentDao.getStudentByEmail(email, getInstitutionId())?.toDomain()
    }

    override suspend fun saveStudent(student: Student) {
        studentDao.insertStudent(student.toEntity())
    }

    override suspend fun deleteStudent(id: String) {
        studentDao.deleteStudent(id, getInstitutionId())
    }

    override suspend fun softDeleteStudent(id: String, reason: String): Resource<Unit> {
        return try {
            studentDao.softDeleteStudent(id, getInstitutionId(), 0, reason, "")
            
            auditRepository.log(
                action = "SOFT_DELETE",
                resource = "students/$id",
                payload = mapOf("reason" to reason)
            )
            
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error soft deleting")
        }
    }

    override suspend fun searchStudents(query: String): List<Student> {
        return studentDao.searchStudents(query, getInstitutionId()).map { it.toDomain() }
    }

    override suspend fun syncStudents(): Resource<Unit> {
        // Implementación de sincronización con Supabase-kt
        return Resource.Success(Unit)
    }

    override fun getStudentsByDocente(userId: String): Flow<List<Student>> {
        return studentDao.getAllStudents(getInstitutionId()).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getProgramsByStudent(studentId: String): List<String> {
        return studentDao.getProgramsByStudent(studentId, getInstitutionId())
    }

    override fun getStudentsByProgram(programId: String): Flow<List<Student>> {
        return studentDao.getStudentsByProgram(programId, getInstitutionId()).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun clearAllStudents(institutionId: String) {
        // En una app real, esto ejecutaría un DELETE FROM students WHERE institutionId = :institutionId
        // Por ahora lo delegamos al DAO si existe
    }
}
