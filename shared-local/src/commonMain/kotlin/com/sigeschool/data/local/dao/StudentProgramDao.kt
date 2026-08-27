package com.sigeschool.data.local.dao

import androidx.room.*
import com.sigeschool.data.local.entity.StudentProgramEntity

@Dao
interface StudentProgramDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enrollStudent(studentProgram: StudentProgramEntity)

    @Query("SELECT * FROM student_programs WHERE studentId = :studentId")
    suspend fun getProgramsForStudent(studentId: String): List<StudentProgramEntity>
}
