package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.PerfilPersonalEntity
import com.sigeschool.data.local.entity.HistorialCvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalProfileDao {
    @Query("SELECT * FROM perfiles_personal")
    fun getAllProfiles(): Flow<List<PerfilPersonalEntity>>

    @Query("SELECT * FROM perfiles_personal WHERE idUsuario = :userId")
    suspend fun getProfileByUserId(userId: String): PerfilPersonalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: PerfilPersonalEntity)

    @Update
    suspend fun updateProfile(profile: PerfilPersonalEntity)

    @Insert
    suspend fun insertHistory(history: HistorialCvEntity)

    @Query("SELECT * FROM historial_cv WHERE idPerfil = :profileId ORDER BY version DESC")
    fun getHistoryForProfile(profileId: String): Flow<List<HistorialCvEntity>>
}
