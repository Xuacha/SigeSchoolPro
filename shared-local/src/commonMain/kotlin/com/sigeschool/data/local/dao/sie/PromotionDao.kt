package com.sigeschool.data.local.dao.sie

import androidx.room.*
import com.sigeschool.data.local.entity.sie.AutoevaluacionEntity
import com.sigeschool.data.local.entity.sie.PromotionConfigEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PromotionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAutoevaluacion(autoevaluacion: AutoevaluacionEntity)

    @Query("SELECT * FROM autoevaluaciones WHERE studentId = :studentId AND periodId = :periodId")
    fun getAutoevaluaciones(studentId: String, periodId: String): Flow<List<AutoevaluacionEntity>>

    @Query("SELECT COUNT(*) FROM autoevaluaciones WHERE studentId = :studentId AND subjectId = :subjectId AND periodId = :periodId")
    suspend fun countAutoevaluacion(studentId: String, subjectId: String, periodId: String): Int

    @Query("SELECT * FROM configuracion_promocion WHERE institutionId = :institutionId LIMIT 1")
    fun getPromotionConfig(institutionId: String): Flow<PromotionConfigEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPromotionConfig(config: PromotionConfigEntity)
}
