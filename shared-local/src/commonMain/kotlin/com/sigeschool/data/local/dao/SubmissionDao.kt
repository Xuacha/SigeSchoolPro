package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.EntregaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubmissionDao {
    @Query("SELECT * FROM task_submissions WHERE tareaId = :tareaId")
    fun getSubmissionsByTask(tareaId: String): Flow<List<EntregaEntity>>

    @Query("SELECT * FROM task_submissions WHERE tareaId = :tareaId AND estudianteId = :estudianteId")
    suspend fun getSubmission(tareaId: String, estudianteId: String): EntregaEntity?

    @Query("SELECT * FROM task_submissions WHERE id = :id")
    suspend fun getSubmissionById(id: String): EntregaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubmission(submission: EntregaEntity)

    @Update
    suspend fun updateSubmission(submission: EntregaEntity)

    @Query("SELECT * FROM task_submissions WHERE syncStatus != 0")
    suspend fun getPendingSyncSubmissions(): List<EntregaEntity>

    @Query("UPDATE task_submissions SET syncStatus = 0 WHERE id = :id")
    suspend fun markAsSynced(id: String)
}
