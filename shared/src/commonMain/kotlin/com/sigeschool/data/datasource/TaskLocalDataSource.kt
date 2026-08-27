package com.sigeschool.data.datasource

import com.sigeschool.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {
    fun getTasks(institutionId: String): Flow<List<Task>>
    fun getTasksByClass(classId: String): Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun getUnsyncedTasks(): List<Task>
}
