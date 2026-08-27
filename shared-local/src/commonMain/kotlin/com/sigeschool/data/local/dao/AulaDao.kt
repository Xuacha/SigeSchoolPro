package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.AulaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AulaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AulaEntity): Long

    @Update
    suspend fun update(entity: AulaEntity)

    @Query("SELECT * FROM academic_aulas WHERE institutionId = :instId ORDER BY nombre ASC")
    fun getAll(instId: String): Flow<List<AulaEntity>>

    @Query("SELECT * FROM academic_aulas WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): AulaEntity?

    @Query("DELETE FROM academic_aulas WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_aulas WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AulaEntity>

    @Query("SELECT * FROM academic_aulas WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<AulaEntity>

    @Query("UPDATE academic_aulas SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
