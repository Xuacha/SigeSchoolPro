package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.AcademicGradoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AcademicGradoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AcademicGradoEntity): Long

    @Update
    suspend fun update(entity: AcademicGradoEntity)

    @Query("SELECT * FROM academic_grados WHERE institutionId = :instId AND nivelEducativoId = :nivelId ORDER BY orden ASC")
    fun getByNivel(instId: String, nivelId: Long): Flow<List<AcademicGradoEntity>>

    @Query("SELECT * FROM academic_grados WHERE institutionId = :instId ORDER BY orden ASC")
    fun getAll(instId: String): Flow<List<AcademicGradoEntity>>

    @Query("SELECT * FROM academic_grados WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): AcademicGradoEntity?

    @Query("DELETE FROM academic_grados WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_grados WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<AcademicGradoEntity>

    @Query("SELECT * FROM academic_grados WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<AcademicGradoEntity>

    @Query("UPDATE academic_grados SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
