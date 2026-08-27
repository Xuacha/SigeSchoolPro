package com.sigeschool.domain.repository

import com.sigeschool.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(institutionId: String): Flow<List<Task>>
    suspend fun saveTask(task: Task)
    suspend fun syncWithCloud()
}
