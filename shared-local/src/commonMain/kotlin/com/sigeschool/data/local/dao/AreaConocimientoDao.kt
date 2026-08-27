package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.AreaConocimientoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AreaConocimientoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AreaConocimientoEntity): Long

    @Update
    suspend fun update(entity: AreaConocimientoEntity)

    @Query("SELECT * FROM academic_areas_conocimiento WHERE institutionId = :instId ORDER BY nombre ASC")
    fun getAll(instId: String): Flow<List<AreaConocimientoEntity>>

    @Query("SELECT * FROM academic_areas_conocimiento WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): AreaConocimientoEntity?

    @Query("DELETE FROM academic_areas_conocimiento WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_areas_conocimiento WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AreaConocimientoEntity>

    @Query("SELECT * FROM academic_areas_conocimiento WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<AreaConocimientoEntity>

    @Query("UPDATE academic_areas_conocimiento SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
