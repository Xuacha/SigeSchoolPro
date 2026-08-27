package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.GradeEntity
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GradeDao_Impl(
  __db: RoomDatabase,
) : GradeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfGradeEntity: EntityInsertAdapter<GradeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfGradeEntity = object : EntityInsertAdapter<GradeEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `grades` (`id`,`institutionId`,`studentId`,`courseId`,`subjectId`,`score`,`date`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GradeEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindText(4, entity.courseId)
        statement.bindText(5, entity.subjectId)
        statement.bindDouble(6, entity.score)
        statement.bindLong(7, entity.date)
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
  }

  public override suspend fun insertGrade(grade: GradeEntity): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfGradeEntity.insert(_connection, grade)
  }

  public override fun getGradesByStudent(studentId: String, institutionId: String):
      Flow<List<GradeEntity>> {
    val _sql: String = "SELECT * FROM grades WHERE studentId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("grades")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfCourseId: Int = getColumnIndexOrThrow(_stmt, "courseId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<GradeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GradeEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpCourseId: String
          _tmpCourseId = _stmt.getText(_columnIndexOfCourseId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpScore: Double
          _tmpScore = _stmt.getDouble(_columnIndexOfScore)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              GradeEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpCourseId,_tmpSubjectId,_tmpScore,_tmpDate,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllGradesSync(institutionId: String): List<GradeEntity> {
    val _sql: String = "SELECT * FROM grades WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfCourseId: Int = getColumnIndexOrThrow(_stmt, "courseId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<GradeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GradeEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpCourseId: String
          _tmpCourseId = _stmt.getText(_columnIndexOfCourseId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpScore: Double
          _tmpScore = _stmt.getDouble(_columnIndexOfScore)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              GradeEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpCourseId,_tmpSubjectId,_tmpScore,_tmpDate,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getStudentAverage(studentId: String, institutionId: String): Flow<Double?> {
    val _sql: String = "SELECT AVG(score) FROM grades WHERE studentId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("grades")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _result: Double?
        if (_stmt.step()) {
          val _tmp: Double?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getDouble(0)
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncGrades(institutionId: String): List<GradeEntity> {
    val _sql: String = "SELECT * FROM grades WHERE syncStatus != 0 AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfCourseId: Int = getColumnIndexOrThrow(_stmt, "courseId")
        val _columnIndexOfSubjectId: Int = getColumnIndexOrThrow(_stmt, "subjectId")
        val _columnIndexOfScore: Int = getColumnIndexOrThrow(_stmt, "score")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<GradeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GradeEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpCourseId: String
          _tmpCourseId = _stmt.getText(_columnIndexOfCourseId)
          val _tmpSubjectId: String
          _tmpSubjectId = _stmt.getText(_columnIndexOfSubjectId)
          val _tmpScore: Double
          _tmpScore = _stmt.getDouble(_columnIndexOfScore)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              GradeEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpCourseId,_tmpSubjectId,_tmpScore,_tmpDate,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String = "UPDATE grades SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteGrade(id: String, institutionId: String) {
    val _sql: String = "DELETE FROM grades WHERE id = ? AND institutionId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
