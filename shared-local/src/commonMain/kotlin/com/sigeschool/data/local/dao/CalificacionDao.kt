package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.CalificacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CalificacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CalificacionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CalificacionEntity>)

    @Update
    suspend fun update(entity: CalificacionEntity)

    @Query("SELECT * FROM academic_calificaciones WHERE institutionId = :instId AND claseId = :claseId")
    fun getByClase(instId: String, claseId: Long): Flow<List<CalificacionEntity>>

    @Query("SELECT * FROM academic_calificaciones WHERE institutionId = :instId AND estudianteId = :estudianteId")
    fun getByEstudiante(instId: String, estudianteId: String): Flow<List<CalificacionEntity>>

    @Query("SELECT * FROM academic_calificaciones WHERE institutionId = :instId AND estudianteId = :estudianteId")
    suspend fun getByEstudianteSync(estudianteId: String, instId: String): List<CalificacionEntity>

    @Query("""
        SELECT * FROM academic_calificaciones 
        WHERE institutionId = :instId 
        AND estudianteId = :estudianteId 
        AND claseId = :claseId 
        AND periodoAcademicoId = :periodoId 
        AND corte = :corte
    """)
    suspend fun getSpecific(instId: String, estudianteId: String, claseId: Long, periodoId: Long, corte: Int): CalificacionEntity?

    @Query("DELETE FROM academic_calificaciones WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: String, instId: String)

    @Query("""
        SELECT * FROM academic_calificaciones 
        WHERE syncStatus != 0
        AND institutionId = :instId
    """)
    suspend fun getPendingSync(instId: String): List<CalificacionEntity>

    @Query("SELECT * FROM academic_calificaciones WHERE institutionId = :instId AND syncStatus = 5")
    suspend fun getSyncingRecords(instId: String): List<CalificacionEntity>

    @Query("UPDATE academic_calificaciones SET syncStatus = 5 WHERE id IN (:ids) AND institutionId = :instId")
    suspend fun markAsSyncing(ids: List<String>, instId: String)

    @Query("UPDATE academic_calificaciones SET syncStatus = 0 WHERE id IN (:ids) AND institutionId = :instId")
    suspend fun markAsSynced(ids: List<String>, instId: String)

    @Query("UPDATE academic_calificaciones SET syncStatus = 1 WHERE id IN (:ids) AND institutionId = :instId")
    suspend fun markAsPending(ids: List<String>, instId: String)

    @Query("UPDATE academic_calificaciones SET estudianteId = :targetStudentId, syncStatus = 2 WHERE estudianteId = :sourceStudentId AND institutionId = :instId")
    suspend fun migrateStudentCalificaciones(sourceStudentId: String, targetStudentId: String, instId: String)
}
