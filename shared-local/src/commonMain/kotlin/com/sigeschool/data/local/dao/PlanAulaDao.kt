package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.PlanAulaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanAulaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PlanAulaEntity): Long

    @Update
    suspend fun update(entity: PlanAulaEntity)

    @Query("SELECT * FROM academic_planes_aula WHERE institutionId = :instId AND claseId = :claseId")
    suspend fun getByClase(instId: String, claseId: Long): PlanAulaEntity?

    @Query("SELECT * FROM academic_planes_aula WHERE institutionId = :instId AND docenteId = :docenteId")
    fun getByDocente(instId: String, docenteId: String): Flow<List<PlanAulaEntity>>

    @Query("DELETE FROM academic_planes_aula WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_planes_aula WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<PlanAulaEntity>

    @Query("SELECT * FROM academic_planes_aula WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<PlanAulaEntity>

    @Query("UPDATE academic_planes_aula SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
