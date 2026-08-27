package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.OfertaAcademicaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OfertaAcademicaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: OfertaAcademicaEntity): Long

    @Update
    suspend fun update(entity: OfertaAcademicaEntity)

    @Query("SELECT * FROM academic_ofertas WHERE institutionId = :instId ORDER BY fechaInicio DESC")
    fun getAll(instId: String): Flow<List<OfertaAcademicaEntity>>

    @Query("SELECT * FROM academic_ofertas WHERE institutionId = :instId AND gradoId = :gradoId AND periodoAcademicoId = :periodoId")
    suspend fun getByGradoAndPeriodo(instId: String, gradoId: Long, periodoId: Long): OfertaAcademicaEntity?

    @Query("SELECT * FROM academic_ofertas WHERE institutionId = :instId AND gradoId = :gradoId")
    suspend fun getAllByGrado(instId: String, gradoId: Long): List<OfertaAcademicaEntity>

    @Query("SELECT * FROM academic_ofertas WHERE institutionId = :instId AND estado = 'PUBLICADA'")
    fun getPublicadas(instId: String): Flow<List<OfertaAcademicaEntity>>

    @Query("SELECT * FROM academic_ofertas WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): OfertaAcademicaEntity?

    @Query("DELETE FROM academic_ofertas WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_ofertas WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<OfertaAcademicaEntity>

    @Query("SELECT * FROM academic_ofertas WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<OfertaAcademicaEntity>

    @Query("UPDATE academic_ofertas SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
