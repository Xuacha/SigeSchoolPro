package com.sigeschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sigeschool.data.local.entity.AcademicRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AcademicRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: AcademicRecordEntity)

    @Query("SELECT * FROM academic_records WHERE studentId = :studentId AND institutionId = :institutionId")
    fun getRecordsByStudent(studentId: String, institutionId: String): Flow<List<AcademicRecordEntity>>

    @Query("SELECT * FROM academic_records WHERE institutionId = :institutionId")
    suspend fun getAllRecordsSync(institutionId: String): List<AcademicRecordEntity>

    @Query("SELECT * FROM academic_records WHERE syncStatus != 0 AND institutionId = :institutionId")
    suspend fun getPendingSyncRecords(institutionId: String): List<AcademicRecordEntity>

    @Query("UPDATE academic_records SET syncStatus = 0 WHERE id = :id AND institutionId = :institutionId")
    suspend fun markAsSynced(id: String, institutionId: String)
}
