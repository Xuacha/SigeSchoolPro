package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.PucAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PucAccountDao {
    @Query("SELECT * FROM puc_accounts WHERE institutionId = :institutionId ORDER BY code")
    fun getAllByInstitution(institutionId: String): Flow<List<PucAccountEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accounts: List<PucAccountEntity>)

    @Query("SELECT * FROM puc_accounts WHERE code = :code AND institutionId = :institutionId")
    suspend fun getByCode(code: String, institutionId: String): PucAccountEntity?

    @Query("DELETE FROM puc_accounts WHERE institutionId = :institutionId")
    suspend fun deleteAllByInstitution(institutionId: String)
}
