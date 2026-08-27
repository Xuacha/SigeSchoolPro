package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.AsignaturaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsignaturaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AsignaturaEntity): Long

    @Update
    suspend fun update(entity: AsignaturaEntity)

    @Query("SELECT * FROM academic_asignaturas WHERE institutionId = :instId ORDER BY nombre ASC")
    fun getAll(instId: String): Flow<List<AsignaturaEntity>>

    @Query("SELECT * FROM academic_asignaturas WHERE institutionId = :instId AND areaConocimientoId = :areaId")
    fun getByArea(instId: String, areaId: Long): Flow<List<AsignaturaEntity>>

    @Query("SELECT * FROM academic_asignaturas WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): AsignaturaEntity?

    @Query("SELECT * FROM academic_asignaturas WHERE nombre = :nombre AND institutionId = :instId LIMIT 1")
    suspend fun getByName(nombre: String, instId: String): AsignaturaEntity?

    @Query("DELETE FROM academic_asignaturas WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_asignaturas WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AsignaturaEntity>

    @Query("SELECT * FROM academic_asignaturas WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<AsignaturaEntity>

    @Query("UPDATE academic_asignaturas SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
