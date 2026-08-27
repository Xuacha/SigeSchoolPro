package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.HorarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HorarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HorarioEntity): Long

    @Update
    suspend fun update(entity: HorarioEntity)

    @Query("SELECT * FROM academic_horarios WHERE institutionId = :instId AND claseId = :claseId")
    fun getByClase(instId: String, claseId: Long): Flow<List<HorarioEntity>>

    @Query("""
        SELECT h.* FROM academic_horarios h
        INNER JOIN academic_clases c ON h.claseId = c.id
        INNER JOIN academic_detalles_oferta d ON c.detalleOfertaId = d.id
        WHERE h.institutionId = :instId AND d.docenteId = :docenteId
    """)
    fun getByDocente(instId: String, docenteId: String): Flow<List<HorarioEntity>>

    @Query("DELETE FROM academic_horarios WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("""
        SELECT COUNT(*) FROM academic_horarios 
        WHERE institutionId = :instId 
        AND aulaId = :aulaId 
        AND diaSemana = :dia 
        AND id != :excludeId
        AND (
            (horaInicio < :hFin AND horaFin > :hInicio)
        )
    """)
    suspend fun countAulaConflicts(instId: String, aulaId: Long, dia: Int, hInicio: String, hFin: String, excludeId: Long): Int

    @Query("""
        SELECT COUNT(*) FROM academic_horarios h
        INNER JOIN academic_clases c ON h.claseId = c.id
        INNER JOIN academic_detalles_oferta d ON c.detalleOfertaId = d.id
        WHERE h.institutionId = :instId 
        AND d.docenteId = :docenteId 
        AND h.diaSemana = :dia 
        AND h.id != :excludeId
        AND (
            (h.horaInicio < :hFin AND h.horaFin > :hInicio)
        )
    """)
    suspend fun countDocenteConflicts(instId: String, docenteId: String, dia: Int, hInicio: String, hFin: String, excludeId: Long): Int

    @Query("SELECT * FROM academic_horarios WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): HorarioEntity?

    @Query("""
        SELECT d.docenteId FROM academic_clases c
        INNER JOIN academic_detalles_oferta d ON c.detalleOfertaId = d.id
        WHERE c.id = :claseId AND c.institutionId = :instId
    """)
    suspend fun getDocenteIdForClase(claseId: Long, instId: String): String?

    @Query("SELECT * FROM academic_horarios WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<HorarioEntity>

    @Query("SELECT * FROM academic_horarios WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<HorarioEntity>

    @Query("UPDATE academic_horarios SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
