package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.InstitutionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InstitutionDao {
    @Query("SELECT * FROM institutions WHERE isActive = 1")
    fun getAllActiveInstitutions(): Flow<List<InstitutionEntity>>

    @Query("SELECT * FROM institutions WHERE isActive = 1")
    suspend fun getAllActiveInstitutionsSync(): List<InstitutionEntity>

    @Query("SELECT * FROM institutions WHERE id = :id LIMIT 1")
    suspend fun getInstitutionById(id: String): InstitutionEntity?

    @Query("SELECT estudiantesActivos FROM institutions WHERE id = :id")
    suspend fun getEstudiantesActivos(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstitution(institution: InstitutionEntity)

    @Update
    suspend fun updateInstitution(institution: InstitutionEntity)

    @Query("UPDATE institutions SET isActive = 0 WHERE id = :id")
    suspend fun softDeleteInstitution(id: String)

    @Query("SELECT COUNT(*) FROM institutions")
    suspend fun count(): Int
}
