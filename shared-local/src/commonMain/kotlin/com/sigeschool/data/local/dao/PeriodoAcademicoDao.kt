package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.PeriodoAcademicoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeriodoAcademicoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PeriodoAcademicoEntity): Long

    @Update
    suspend fun update(entity: PeriodoAcademicoEntity)

    @Query("SELECT * FROM academic_periodos WHERE institutionId = :instId ORDER BY fechaInicio DESC")
    fun getAll(instId: String): Flow<List<PeriodoAcademicoEntity>>

    @Query("SELECT * FROM academic_periodos WHERE institutionId = :instId AND esActivo = 1")
    fun getActive(instId: String): Flow<List<PeriodoAcademicoEntity>>

    @Query("SELECT * FROM academic_periodos WHERE institutionId = :instId AND esActivo = 1 LIMIT 1")
    suspend fun getActiveSync(instId: String): PeriodoAcademicoEntity?

    @Query("SELECT * FROM academic_periodos WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): PeriodoAcademicoEntity?

    @Query("SELECT * FROM academic_periodos WHERE nombre = :nombre AND institutionId = :instId LIMIT 1")
    suspend fun getByName(nombre: String, instId: String): PeriodoAcademicoEntity?

    @Query("DELETE FROM academic_periodos WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)

    @Query("SELECT * FROM academic_periodos WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<PeriodoAcademicoEntity>

    @Query("SELECT * FROM academic_periodos WHERE institutionId = :instId")
    suspend fun getAllSync(instId: String): List<PeriodoAcademicoEntity>

    @Query("UPDATE academic_periodos SET syncStatus = 0 WHERE id = :id AND institutionId = :instId")
    suspend fun markAsSynced(id: Long, instId: String)
}
