package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.InstitutionThemeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InstitutionThemeDao {
    @Query("SELECT * FROM institution_themes WHERE institutionId = :institutionId")
    fun getThemeByInstitutionId(institutionId: String): Flow<InstitutionThemeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTheme(theme: InstitutionThemeEntity)

    @Query("DELETE FROM institution_themes WHERE institutionId = :institutionId")
    suspend fun deleteTheme(institutionId: String)
}
