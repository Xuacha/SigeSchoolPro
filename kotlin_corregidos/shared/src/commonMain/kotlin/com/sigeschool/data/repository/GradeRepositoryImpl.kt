package com.sigeschool.data.repository

import com.sigeschool.data.datasource.GradeLocalDataSource
import com.sigeschool.domain.model.Grade
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow

class GradeRepositoryImpl(
    private val localDataSource: GradeLocalDataSource,
    private val supabaseClient: SupabaseClient
) : GradeRepository {

    // FIX CRÍTICO: el filtro usaba el nombre de la propiedad Kotlin
    // "studentId", pero la columna real en Postgres es "student_id"
    // (ver @SerialName("student_id") en el modelo Grade). Postgrest
    // no traduce SerialName automáticamente en `eq()`: este filtro
    // apuntaba a una columna que no existe, así que la consulta de
    // notas por estudiante fallaba (o no devolvía nada) siempre.
    override fun getGradesByStudent(studentId: Long): Flow<Resource<List<Grade>>> = networkBoundResource(
        query = { localDataSource.getGradesByStudent(studentId) },
        fetch = {
            supabaseClient.postgrest["grades"].select {
                filter { eq("student_id", studentId) }
            }.decodeList<Grade>()
        },
        saveFetchResult = { grades ->
            grades.forEach { localDataSource.saveGrade(it.copy(sincronizado = true)) }
        }
    )

    override suspend fun saveGrade(grade: Grade): Resource<Boolean> {
        return try {
            localDataSource.saveGrade(grade.copy(sincronizado = false))
            supabaseClient.postgrest["grades"].upsert(grade)
            localDataSource.saveGrade(grade.copy(sincronizado = true))
            Resource.Success(true)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al guardar nota. Se guardó localmente y se reintentará luego.")
        }
    }

    override suspend fun updateGrade(grade: Grade): Resource<Boolean> {
        return try {
            localDataSource.saveGrade(grade.copy(sincronizado = false))
            supabaseClient.postgrest["grades"].update(grade) {
                filter { eq("id", grade.id) }
            }
            localDataSource.saveGrade(grade.copy(sincronizado = true))
            Resource.Success(true)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar nota")
        }
    }

    override suspend fun deleteGrade(gradeId: Long): Resource<Boolean> {
        return try {
            localDataSource.deleteGrade(gradeId)
            supabaseClient.postgrest["grades"].delete {
                filter { eq("id", gradeId) }
            }
            Resource.Success(true)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al eliminar nota")
        }
    }

    override suspend fun syncWithCloud() {
        try {
            val unsynced = localDataSource.getUnsyncedGrades()
            unsynced.forEach { grade ->
                try {
                    supabaseClient.postgrest["grades"].upsert(grade)
                    localDataSource.saveGrade(grade.copy(sincronizado = true))
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
