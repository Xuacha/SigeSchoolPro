package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.PeriodoConfiguracionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeriodoConfiguracionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: PeriodoConfiguracionEntity): Long

    @Query("SELECT * FROM periodo_configuracion WHERE periodoAcademicoId = :periodoId AND institutionId = :instId")
    fun getByPeriodo(periodoId: Long, instId: String): Flow<List<PeriodoConfiguracionEntity>>

    @Query("DELETE FROM periodo_configuracion WHERE periodoAcademicoId = :periodoId AND institutionId = :instId")
    suspend fun deleteByPeriodo(periodoId: Long, instId: String)
    
    @Query("SELECT * FROM periodo_configuracion WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<PeriodoConfiguracionEntity>
}
