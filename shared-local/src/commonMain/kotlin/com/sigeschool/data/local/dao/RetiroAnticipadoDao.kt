package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.RetiroAnticipadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RetiroAnticipadoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(retiro: RetiroAnticipadoEntity): Long

    @Update
    suspend fun update(retiro: RetiroAnticipadoEntity)

    @Query("SELECT * FROM retiros_anticipados WHERE studentId = :studentId AND institutionId = :instId ORDER BY fechaSalida DESC")
    fun getByStudent(studentId: String, instId: String): Flow<List<RetiroAnticipadoEntity>>

    @Query("SELECT * FROM retiros_anticipados WHERE institutionId = :instId AND syncStatus != 0")
    suspend fun getPendingSync(instId: String): List<RetiroAnticipadoEntity>
}
