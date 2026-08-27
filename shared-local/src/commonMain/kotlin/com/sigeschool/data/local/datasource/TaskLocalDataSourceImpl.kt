package com.sigeschool.data.local.datasource

import com.sigeschool.data.datasource.TaskLocalDataSource
import com.sigeschool.data.local.dao.TaskDao
import com.sigeschool.data.local.mapper.toDomain
import com.sigeschool.data.local.mapper.toEntity
import com.sigeschool.domain.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskLocalDataSourceImpl(
    private val taskDao: TaskDao
) : TaskLocalDataSource {
    override fun getTasks(institutionId: String): Flow<List<Task>> {
        return taskDao.getTasks(institutionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getTasksByClass(classId: String): Flow<List<Task>> {
        return taskDao.getTasksByClass(classId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.toEntity())
    }

    override suspend fun getUnsyncedTasks(): List<Task> {
        return taskDao.getUnsyncedTasks().map { it.toDomain() }
    }
}
