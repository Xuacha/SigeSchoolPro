package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ClassEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao {
    @Query("SELECT * FROM classes WHERE institutionId = :institutionId")
    fun getAllByInstitution(institutionId: String): Flow<List<ClassEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clazz: ClassEntity)

    @Update
    suspend fun update(clazz: ClassEntity)

    @Delete
    suspend fun delete(clazz: ClassEntity)
}
