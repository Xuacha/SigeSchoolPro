package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.DocenteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DocenteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(docente: DocenteEntity): Long

    @Update
    suspend fun update(docente: DocenteEntity)

    @Query("SELECT * FROM employee_docentes WHERE institutionId = :instId AND estado = 'ACTIVO'")
    fun getAll(instId: String): Flow<List<DocenteEntity>>

    @Query("SELECT * FROM employee_docentes WHERE userId = :userId AND institutionId = :instId")
    suspend fun getByUserId(userId: String, instId: String): DocenteEntity?

    @Query("SELECT * FROM employee_docentes WHERE id = :id AND institutionId = :instId")
    suspend fun getById(id: Long, instId: String): DocenteEntity?

    @Query("DELETE FROM employee_docentes WHERE id = :id AND institutionId = :instId")
    suspend fun deleteById(id: Long, instId: String)
}
