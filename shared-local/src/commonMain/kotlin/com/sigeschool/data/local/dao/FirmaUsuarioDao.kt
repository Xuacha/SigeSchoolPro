package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.FirmaUsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FirmaUsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(firma: FirmaUsuarioEntity)

    @Query("SELECT * FROM firmas_usuarios WHERE userId = :userId AND institutionId = :instId LIMIT 1")
    suspend fun getByUsuario(userId: String, instId: String): FirmaUsuarioEntity?

    @Query("DELETE FROM firmas_usuarios WHERE userId = :userId AND institutionId = :instId")
    suspend fun deleteByUsuario(userId: String, instId: String)
}
