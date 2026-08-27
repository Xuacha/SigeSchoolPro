package com.sigeschool.data.repository

import com.sigeschool.data.datasource.TaskLocalDataSource
import com.sigeschool.data.remote.TaskRemoteDataSource
import com.sigeschool.domain.model.Task
import com.sigeschool.domain.util.Resource
import com.sigeschool.domain.util.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskRepositoryImpl(
    private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource
) : TaskRepository {

    override fun getTasks(institutionId: String): Flow<Resource<List<Task>>> {
        return networkBoundResource(
            query = { localDataSource.getTasks(institutionId) },
            fetch = { remoteDataSource.getTasks(institutionId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertTask(it.copy(sincronizado = true)) }
            }
        )
    }

    override fun getTasksByClass(classId: String): Flow<Resource<List<Task>>> {
        return networkBoundResource(
            query = { localDataSource.getTasksByClass(classId) },
            fetch = { remoteDataSource.getTasksByClass(classId) },
            saveFetchResult = { remoteData ->
                remoteData.forEach { localDataSource.insertTask(it.copy(sincronizado = true)) }
            }
        )
    }

    override suspend fun addTask(task: Task, evidenceBytes: ByteArray?): Resource<Boolean> {
        return try {
            localDataSource.insertTask(task.copy(sincronizado = false))
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.upsertTask(task)
            }
            if (success) {
                localDataSource.insertTask(task.copy(sincronizado = true))
                Resource.Success(true)
            } else {
                Resource.Error("Guardado localmente. Se sincronizará al conectar.", true)
            }
        } catch (e: Exception) {
            Resource.Error("Error al agregar tarea: ${e.message}")
        }
    }

    override suspend fun deleteTask(task: Task): Resource<Boolean> {
        return try {
            localDataSource.deleteTask(task)
            val success = withContext(Dispatchers.Default) {
                remoteDataSource.deleteTask(task.id)
            }
            Resource.Success(success)
        } catch (e: Exception) {
            Resource.Error("Error al eliminar: ${e.message}")
        }
    }

    override suspend fun syncWithCloud() {
        withContext(Dispatchers.Default) {
            try {
                val unsynced = localDataSource.getUnsyncedTasks()
                unsynced.forEach { task ->
                    if (remoteDataSource.upsertTask(task)) {
                        localDataSource.insertTask(task.copy(sincronizado = true))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun getTaskById(id: String): Task? {
        return localDataSource.getTaskById(id)
    }

    override fun getAttachments(taskId: String): Flow<List<String>> {
        // Simulación: En una app real, esto consultaría una tabla de adjuntos
        return kotlinx.coroutines.flow.flowOf(emptyList())
    }
}
