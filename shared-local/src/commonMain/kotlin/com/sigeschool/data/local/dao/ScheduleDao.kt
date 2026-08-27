package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.ClassroomEntity
import com.sigeschool.data.local.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM classrooms WHERE institutionId = :institutionId")
    fun getAllClassrooms(institutionId: String): Flow<List<ClassroomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassroom(classroom: ClassroomEntity)

    @Query("SELECT * FROM schedules WHERE institutionId = :institutionId")
    fun getAllSchedules(institutionId: String): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM schedules WHERE classroomId = :classroomId AND institutionId = :institutionId")
    fun getSchedulesByClassroom(classroomId: String, institutionId: String): Flow<List<ScheduleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity)

    @Query("DELETE FROM schedules WHERE id = :id AND institutionId = :institutionId")
    suspend fun deleteScheduleById(id: String, institutionId: String)
}
