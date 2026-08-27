package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sigeschool.data.local.entity.InstitutionSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InstitutionSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settings: InstitutionSettingsEntity): Long

    @Update
    suspend fun update(settings: InstitutionSettingsEntity)

    @Query("SELECT * FROM institution_settings WHERE institutionId = :instId")
    fun getByInstitution(instId: String): Flow<InstitutionSettingsEntity?>

    @Query("SELECT * FROM institution_settings WHERE institutionId = :instId")
    suspend fun getByInstitutionSuspend(instId: String): InstitutionSettingsEntity?
}
