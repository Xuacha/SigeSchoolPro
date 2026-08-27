package com.sigeschool.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.sigeschool.`data`.local.entity.AcademicRecordEntity
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
public class AcademicRecordDao_Impl(
  __db: RoomDatabase,
) : AcademicRecordDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfAcademicRecordEntity: EntityInsertAdapter<AcademicRecordEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAcademicRecordEntity = object :
        EntityInsertAdapter<AcademicRecordEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `academic_records` (`id`,`institutionId`,`studentId`,`year`,`grade`,`gpa`,`status`,`syncStatus`,`lastModified`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AcademicRecordEntity) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.institutionId)
        statement.bindText(3, entity.studentId)
        statement.bindLong(4, entity.year.toLong())
        statement.bindText(5, entity.grade)
        statement.bindDouble(6, entity.gpa)
        statement.bindText(7, entity.status)
        statement.bindLong(8, entity.syncStatus.toLong())
        statement.bindLong(9, entity.lastModified)
      }
    }
  }

  public override suspend fun insertRecord(record: AcademicRecordEntity): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfAcademicRecordEntity.insert(_connection, record)
  }

  public override fun getRecordsByStudent(studentId: String, institutionId: String):
      Flow<List<AcademicRecordEntity>> {
    val _sql: String = "SELECT * FROM academic_records WHERE studentId = ? AND institutionId = ?"
    return createFlow(__db, false, arrayOf("academic_records")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, studentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfGpa: Int = getColumnIndexOrThrow(_stmt, "gpa")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicRecordEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpGrade: String
          _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          val _tmpGpa: Double
          _tmpGpa = _stmt.getDouble(_columnIndexOfGpa)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicRecordEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpYear,_tmpGrade,_tmpGpa,_tmpStatus,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllRecordsSync(institutionId: String): List<AcademicRecordEntity> {
    val _sql: String = "SELECT * FROM academic_records WHERE institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfGpa: Int = getColumnIndexOrThrow(_stmt, "gpa")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicRecordEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpGrade: String
          _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          val _tmpGpa: Double
          _tmpGpa = _stmt.getDouble(_columnIndexOfGpa)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicRecordEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpYear,_tmpGrade,_tmpGpa,_tmpStatus,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingSyncRecords(institutionId: String):
      List<AcademicRecordEntity> {
    val _sql: String = "SELECT * FROM academic_records WHERE syncStatus != 0 AND institutionId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, institutionId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInstitutionId: Int = getColumnIndexOrThrow(_stmt, "institutionId")
        val _columnIndexOfStudentId: Int = getColumnIndexOrThrow(_stmt, "studentId")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfGrade: Int = getColumnIndexOrThrow(_stmt, "grade")
        val _columnIndexOfGpa: Int = getColumnIndexOrThrow(_stmt, "gpa")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSyncStatus: Int = getColumnIndexOrThrow(_stmt, "syncStatus")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "lastModified")
        val _result: MutableList<AcademicRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AcademicRecordEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpInstitutionId: String
          _tmpInstitutionId = _stmt.getText(_columnIndexOfInstitutionId)
          val _tmpStudentId: String
          _tmpStudentId = _stmt.getText(_columnIndexOfStudentId)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpGrade: String
          _tmpGrade = _stmt.getText(_columnIndexOfGrade)
          val _tmpGpa: Double
          _tmpGpa = _stmt.getDouble(_columnIndexOfGpa)
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpSyncStatus: Int
          _tmpSyncStatus = _stmt.getLong(_columnIndexOfSyncStatus).toInt()
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          _item =
              AcademicRecordEntity(_tmpId,_tmpInstitutionId,_tmpStudentId,_tmpYear,_tmpGrade,_tmpGpa,_tmpStatus,_tmpSyncStatus,_tmpLastModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markAsSynced(id: String, institutionId: String) {
    val _sql: String =
        "UPDATE academic_records SET syncStatus = 0 WHERE id = ? AND institutionId = ?"
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
