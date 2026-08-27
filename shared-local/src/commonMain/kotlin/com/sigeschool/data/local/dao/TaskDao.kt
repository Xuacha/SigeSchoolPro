package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.TareaEntity
import com.sigeschool.data.local.entity.TareaAdjuntoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tareas WHERE institutionId = :institutionId AND claseId = :claseId")
    fun getTasksByClase(institutionId: String, claseId: Long): Flow<List<TareaEntity>>

    @Query("SELECT * FROM tareas WHERE id = :id AND institutionId = :institutionId")
    suspend fun getTaskById(id: String, institutionId: String): TareaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TareaEntity)

    @Update
    suspend fun updateTask(task: TareaEntity)

    @Query("DELETE FROM tareas WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteTask(id: String, institutionId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachments(attachments: List<TareaAdjuntoEntity>)

    @Query("SELECT * FROM task_attachments WHERE parentId = :parentId")
    fun getAttachments(parentId: String): Flow<List<TareaAdjuntoEntity>>

    @Query("DELETE FROM task_attachments WHERE parentId = :parentId")
    suspend fun deleteAttachmentsByParent(parentId: String)

    @Query("SELECT * FROM tareas WHERE institutionId = :institutionId AND syncStatus != 0")
    suspend fun getPendingSyncTasks(institutionId: String): List<TareaEntity>

    @Query("UPDATE tareas SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)
}
