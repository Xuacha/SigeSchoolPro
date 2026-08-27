package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.PlanEstudiosEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanEstudiosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PlanEstudiosEntity): Long

    @Update
    suspend fun update(entity: PlanEstudiosEntity)

    @Query("SELECT * FROM academic_planes_estudios WHERE institutionId = :instId AND vigente = 1 LIMIT 1")
    suspend fun getVigente(instId: String): PlanEstudiosEntity?

    @Query("SELECT * FROM academic_planes_estudios WHERE institutionId = :instId ORDER BY id DESC")
    fun getAll(instId: String): Flow<List<PlanEstudiosEntity>>

    @Query("SELECT * FROM academic_planes_estudios WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): PlanEstudiosEntity?

    @Query("DELETE FROM academic_planes_estudios WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_planes_estudios WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<PlanEstudiosEntity>

    @Query("SELECT * FROM academic_planes_estudios WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<PlanEstudiosEntity>

    @Query("UPDATE academic_planes_estudios SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
