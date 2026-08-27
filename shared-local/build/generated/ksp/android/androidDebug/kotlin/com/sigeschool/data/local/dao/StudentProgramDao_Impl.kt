package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.StudentProgramEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class StudentProgramDao_Impl(
  __db: RoomDatabase,
) : StudentProgramDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfStudentProgramEntity: EntityInsertAdapter<StudentProgramEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfStudentProgramEntity = object :
        EntityInsertAdapter<StudentProgramEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `student_programs` (`studentId`,`programId`,`institutionId`,`enrollmentDate`,`status`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: StudentProgramEntity) {
        statement.bindText(1, entity.studentId)
        statement.bindText(2, entity.programId)
        statement.bindText(3, entity.institutionId)
        statement.bindLong(4, entity.enrollmentDate)
        statement.bindText(5, entity.status)
      }
    }
  }

  public override suspend fun enrollStudent(studentProgram: StudentProgramEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfStudentProgramEntity.insert(_connection, studentProgram)
  }

  public override suspend fun getProgramsForStudent(studentId: String): List<StudentProgramEntity> {
    val _sql: String = "SELECT * FROM student_programs WHERE studentId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfProgramId: Int = getColumnIndexOrThrow(_stmt, "programId")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfEnrollmentDate: Int = getColumnIndexOrThrow(_stmt, "enrollmentDate")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _result: MutableList<StudentProgramEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentProgramEntity
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpProgramId: String
          _tmpProgramId = _stmt.getText(_columnIndexOfProgramId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpEnrollmentDate: Long
          _tmpEnrollmentDate = _stmt.getLong(_columnIndexOfEnrollmentDate)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          _item =
              StudentProgramEntity(_tmpStudentId,_tmpProgramId,_tmpInstitutionId,_tmpEnrollmentDate,_tmpStatus)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
