package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.PlanEstudiosDetalleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanEstudiosDetalleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PlanEstudiosDetalleEntity): Long

    @Update
    suspend fun update(entity: PlanEstudiosDetalleEntity)

    @Query("SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = :instId AND planEstudiosId = :planId")
    fun getByPlan(instId: String, planId: Long): Flow<List<PlanEstudiosDetalleEntity>>

    @Query("SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = :instId AND gradoId = :gradoId")
    fun getByGrado(instId: String, gradoId: Long): Flow<List<PlanEstudiosDetalleEntity>>

    @Query("DELETE FROM academic_planes_estudios_detalle WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("DELETE FROM academic_planes_estudios_detalle WHERE planEstudiosId = :planId AND institutionId = :instId")
    suspend fun deleteByPlan(planId: Long, instId: String)

    @Query("SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<PlanEstudiosDetalleEntity>

    @Query("SELECT * FROM academic_planes_estudios_detalle WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<PlanEstudiosDetalleEntity>

    @Query("UPDATE academic_planes_estudios_detalle SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
