package com.sigeschool.data.repository

import com.sigeschool.data.datasource.ExamLocalDataSource
import com.sigeschool.data.remote.ExamRemoteDataSource
import com.sigeschool.domain.model.Exam
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ExamRepositoryImpl(
    private val localDataSource: ExamLocalDataSource,
    private val remoteDataSource: ExamRemoteDataSource
) : ExamRepository {

    override fun getExams(institutionId: String): Flow<Resource<List<Exam>>> {
        return networkBoundResource(
            query = { localDataSource.getExams(institutionId) },
            fetch = { remoteDataSource.getExams(institutionId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertExam(it.copy(sincronizado = true)) }
            }
        )
    }

    override fun getExamsByClass(classId: String): Flow<Resource<List<Exam>>> {
        return networkBoundResource(
            query = { localDataSource.getExamsByClass(classId) },
            fetch = { remoteDataSource.getExamsByClass(classId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertExam(it.copy(sincronizado = true)) }
            }
        )
    }

    override suspend fun addExam(exam: Exam): Resource<Boolean> {
        return try {
            localDataSource.insertExam(exam.copy(sincronizado = false))
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.upsertExam(exam)
            }
            if (success) {
                localDataSource.insertExam(exam.copy(sincronizado = true))
                Resource.Success(true)
            } else {
                Resource.Error("Examen guardado localmente.", true)
            }
        } catch (e: Exception) {
            Resource.Error("Error al guardar examen: ${e.message}")
        }
    }

    override suspend fun deleteExam(exam: Exam): Resource<Boolean> {
        return try {
            localDataSource.deleteExam(exam)
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.deleteExam(exam.id)
            }
            Resource.Success(success)
        } catch (e: Exception) {
            Resource.Error("Error al eliminar examen")
        }
    }

    override suspend fun syncWithCloud() {
        withContext(Dispatchers.Default) {
            try {
                val unsynced = localDataSource.getUnsyncedExams()
                unsynced.forEach { exam ->
                    if (remoteDataSource.upsertExam(exam)) {
                        localDataSource.insertExam(exam.copy(sincronizado = true))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun saveExamResult(examId: String, score: Double, studentId: String): Resource<Boolean> {
        return try {
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.saveResult(examId, score, studentId)
            }
            Resource.Success(success)
        } catch (e: Exception) {
            Resource.Error("Error al guardar resultado")
        }
    }
}
