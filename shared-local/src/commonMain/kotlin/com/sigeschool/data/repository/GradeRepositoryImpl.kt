package com.sigeschool.data.repository

import com.sigeschool.data.local.database.AppDatabase
import com.sigeschool.data.mapper.toDomain
import com.sigeschool.data.mapper.toEntity
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.repository.GradeRepository
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.SessionManager
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GradeRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val sessionManager: SessionManager,
    private val database: AppDatabase
) : GradeRepository {

    private val calificacionDao = database.calificacionDao()

    private fun getInstitutionId(): String {
        return sessionManager.getCurrentInstitutionId() ?: throw Exception("Sesión no encontrada")
    }

    override fun getGradesByStudent(studentId: String, institutionId: String): Flow<Resource<List<Grade>>> {
        return calificacionDao.getByEstudiante(institutionId, studentId).map { list ->
            Resource.Success(list.map { it.toDomain() })
        }
    }

    override fun getGradesByClase(claseId: Long): Flow<List<Grade>> {
        return calificacionDao.getByClase(getInstitutionId(), claseId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveGrade(grade: Grade, institutionId: String): Resource<Boolean> {
        return try {
            calificacionDao.insert(grade.toEntity().copy(institutionId = institutionId))
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error saving grade")
        }
    }

    override suspend fun updateGrade(grade: Grade, institutionId: String): Resource<Boolean> {
        return try {
            calificacionDao.update(grade.toEntity().copy(institutionId = institutionId))
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error updating grade")
        }
    }

    override suspend fun deleteGrade(gradeId: String, institutionId: String): Resource<Boolean> {
        return try {
            calificacionDao.deleteById(gradeId, institutionId)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error deleting grade")
        }
    }

    override suspend fun syncGrades(): Resource<Unit> {
        return Resource.Success(Unit)
    }
}
