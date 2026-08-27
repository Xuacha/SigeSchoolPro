package com.sigeschool.data.repository

import com.sigeschool.data.datasource.StudentLocalDataSource
import com.sigeschool.data.remote.StudentRemoteDataSource
import com.sigeschool.domain.model.Student
import com.sigeschool.domain.model.StudentStatus
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class StudentRepositoryImpl(
    private val localDataSource: StudentLocalDataSource,
    private val remoteDataSource: StudentRemoteDataSource
) : StudentRepository {

    // FIX: ahora exige institutionId y lo propaga tanto a la consulta
    // local (Room) como remota (Supabase). Antes traía estudiantes de
    // cualquier institución guardada en el dispositivo.
    override fun getAllStudents(institutionId: String): Flow<Resource<List<Student>>> {
        return networkBoundResource(
            query = { localDataSource.getAllStudents(institutionId) },
            fetch = { remoteDataSource.getAllStudents(institutionId) },
            saveFetchResult = { students ->
                students.forEach { student ->
                    localDataSource.insertStudent(student.copy(sincronizado = true))
                }
            },
            shouldFetch = { true } // Siempre intenta refrescar en segundo plano si hay red
        )
    }

    override suspend fun addStudent(student: Student): Resource<Boolean> {
        return try {
            localDataSource.insertStudent(student.copy(sincronizado = false))
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.uploadStudent(student)
            }
            if (success) {
                localDataSource.insertStudent(student.copy(sincronizado = true))
                Resource.Success(true)
            } else {
                Resource.Error("Guardado localmente, pendiente de sincronización", true)
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error desconocido")
        }
    }

    override suspend fun updateStudent(student: Student): Resource<Boolean> {
        return try {
            localDataSource.insertStudent(student.copy(sincronizado = false))
            val success = remoteDataSource.updateStudent(student)
            if (success) {
                localDataSource.insertStudent(student.copy(sincronizado = true))
                Resource.Success(true)
            } else {
                Resource.Error("Actualización local completada, pendiente de sincronización", true)
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error al actualizar")
        }
    }

    override suspend fun deleteStudent(id: Long): Resource<Boolean> {
        return try {
            val student = localDataSource.getStudentById(id)
                ?: return Resource.Error("Estudiante no encontrado")
            val updated = student.copy(estadoMatricula = StudentStatus.RETIRADO, activo = false)
            updateStudent(updated)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Error("Error al retirar estudiante")
        }
    }

    override suspend fun getStudentById(id: Long): Student? {
        return localDataSource.getStudentById(id)
    }

    override suspend fun syncWithSupabase(institutionId: String) {
        withContext(Dispatchers.Default) {
            try {
                // Sincronizar cambios locales no subidos
                val unsynced = localDataSource.getUnsyncedStudents()
                    .filter { it.institutionId == institutionId }
                unsynced.forEach { student ->
                    if (remoteDataSource.uploadStudent(student)) {
                        localDataSource.insertStudent(student.copy(sincronizado = true))
                    }
                }
                // Refrescar datos locales con los remotos
                val remoteStudents = remoteDataSource.getAllStudents(institutionId)
                remoteStudents.forEach { student ->
                    localDataSource.insertStudent(student.copy(sincronizado = true))
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
