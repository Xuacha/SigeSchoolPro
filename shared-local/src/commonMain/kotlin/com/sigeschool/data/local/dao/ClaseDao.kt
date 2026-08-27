package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.ClaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ClaseEntity): Long

    @Update
    suspend fun update(entity: ClaseEntity)

    @Query("SELECT * FROM academic_clases WHERE institutionId = :instId AND ofertaAcademicaId = :ofertaId")
    fun getByOferta(instId: String, ofertaId: Long): Flow<List<ClaseEntity>>

    @Query("SELECT * FROM academic_clases WHERE institutionId = :instId AND ofertaAcademicaId = :ofertaId")
    suspend fun getByOfertaSync(instId: String, ofertaId: Long): List<ClaseEntity>

    @Query("SELECT * FROM academic_clases WHERE institutionId = :instId AND detalleOfertaId = :detalleId")
    suspend fun getByDetalle(instId: String, detalleId: Long): ClaseEntity?

    @Query("SELECT * FROM academic_clases WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): ClaseEntity?

    @Query("UPDATE academic_clases SET estudiantesInscritos = estudiantesInscritos + 1 WHERE id = :id AND institutionId = :instId")
    suspend fun incrementarInscritos(id: Long, instId: String)

    @Query("UPDATE academic_clases SET estudiantesInscritos = estudiantesInscritos - 1 WHERE id = :id AND institutionId = :instId")
    suspend fun decrementarInscritos(id: Long, instId: String)

    @Query("DELETE FROM academic_clases WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_clases WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<ClaseEntity>

    @Query("SELECT * FROM academic_clases WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<ClaseEntity>

    @Query("UPDATE academic_clases SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
