package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ListadoConfigEntity

@Dao
interface ListadoConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(config: ListadoConfigEntity)

    @Query("SELECT * FROM listado_config WHERE institutionId = :instId LIMIT 1")
    suspend fun getByInstitution(instId: String): ListadoConfigEntity?
}
