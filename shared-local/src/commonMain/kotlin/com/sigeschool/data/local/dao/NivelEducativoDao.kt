package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.NivelEducativoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NivelEducativoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NivelEducativoEntity): Long

    @Update
    suspend fun update(entity: NivelEducativoEntity)

    @Query("SELECT * FROM academic_niveles_educativos WHERE institutionId = :instId ORDER BY orden ASC")
    fun getAll(instId: String): Flow<List<NivelEducativoEntity>>

    @Query("SELECT * FROM academic_niveles_educativos WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): NivelEducativoEntity?

    @Query("DELETE FROM academic_niveles_educativos WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_niveles_educativos WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<NivelEducativoEntity>

    @Query("SELECT * FROM academic_niveles_educativos WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<NivelEducativoEntity>

    @Query("UPDATE academic_niveles_educativos SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
