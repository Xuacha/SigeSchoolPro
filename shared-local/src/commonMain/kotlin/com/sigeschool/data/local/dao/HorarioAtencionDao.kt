package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.HorarioAtencionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HorarioAtencionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(horario: HorarioAtencionEntity)

    @Query("SELECT * FROM horarios_atencion WHERE docenteId = :docenteId AND institutionId = :instId")
    fun getByDocente(docenteId: String, instId: String): Flow<List<HorarioAtencionEntity>>

    @Query("SELECT * FROM horarios_atencion WHERE institutionId = :instId")
    fun getAllByInstitution(instId: String): Flow<List<HorarioAtencionEntity>>

    @Delete
    suspend fun delete(horario: HorarioAtencionEntity)
}
