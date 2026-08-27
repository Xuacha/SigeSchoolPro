package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.JornadaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JornadaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jornada: JornadaEntity): Long

    @Update
    suspend fun update(jornada: JornadaEntity)

    @Query("SELECT * FROM academic_jornadas WHERE institutionId = :instId AND activa = 1 ORDER BY nombre ASC")
    fun getAll(instId: String): Flow<List<JornadaEntity>>

    @Query("SELECT * FROM academic_jornadas WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): JornadaEntity?

    @Query("DELETE FROM academic_jornadas WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
